package br.com.lighthost.kotlinDactylApi.client.server.managers.subusers

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.subusers.models.ClientSubUserModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ClientSubUsersManager(private val server: ClientServerDetails, private val baseRequest: BaseRequest) {

    fun retrieveSubUsers(): MutableList<ClientSubUserModel> {
        val list:MutableList<ClientSubUserModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.SUBUSERS.listSubUsers(server.attributes.identifier), null)).getJSONArray("data").forEach{
            it as JSONObject
             list.add(parseClientSubUser(it.getJSONObject("attributes").toString()))
        }
        return list
    }

    fun retrieveSubUser(uuid:String):ClientSubUserModel{
        return parseClientSubUser(JSONObject(baseRequest.executeRequest(
            ClientRoutes.SUBUSERS.getSubUser(server.attributes.identifier, uuid), null))
            .getJSONObject("attributes").toString())
    }

    fun createSubUser(email:String, permissions:List<String>):ClientSubUserModel{
        val json = JSONObject().accumulate("email", email).accumulate("permissions", permissions)
        return parseClientSubUser(JSONObject(baseRequest.executeRequest(
            ClientRoutes.SUBUSERS.createSubUser(server.attributes.identifier), json.toString()))
            .getJSONObject("attributes").toString())
    }

    private fun parseClientSubUser(rawJson : String): ClientSubUserModel {
        val json = JSONObject(rawJson)
        return ClientSubUserModel(
            json.getString("uuid"),
            json.getString("username"),
            json.getString("email"),
            json.getString("image"),
            json.getBoolean("2fa_enabled"),
            OffsetDateTime.parse(json.getString("created_at")),
            json.getJSONArray("permissions").toMutableList().map { it as String } as MutableList<String>,
            baseRequest,
            server)
    }

}