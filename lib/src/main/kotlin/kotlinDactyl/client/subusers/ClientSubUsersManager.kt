package kotlinDactyl.client.subusers

import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.client.startup.models.EnviromentVariableModel
import kotlinDactyl.client.subusers.actions.SubUsersActions
import kotlinDactyl.client.subusers.models.ClientSubUserModel
import kotlinDactyl.requests.BaseRequest
import kotlinDactyl.requests.RouteModels.ClientRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ClientSubUsersManager(private val server: ClientServerDetails, private val baseRequest: BaseRequest) {

    fun retrieveSubUsers(): MutableList<ClientSubUserModel> {
        val list:MutableList<ClientSubUserModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.SUBUSERS.listSubUsers(server.identifier), null)).getJSONArray("data").forEach{
            it as JSONObject
             list.add(parseClientSubUser(it.getJSONObject("attributes").toString()))
        }
        return list
    }

    fun retrieveSubUser(uuid:String):ClientSubUserModel{
        return parseClientSubUser(JSONObject(baseRequest.executeRequest(
            ClientRoutes.SUBUSERS.getSubUser(server.identifier, uuid), null))
            .getJSONObject("attributes").toString())
    }

    fun createSubUser(email:String, permissions:List<String>):ClientSubUserModel{
        val json = JSONObject().accumulate("email", email).accumulate("permissions", permissions)
        return parseClientSubUser(JSONObject(baseRequest.executeRequest(
            ClientRoutes.SUBUSERS.createSubUser(server.identifier), json.toString()))
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
            json.getJSONArray("permissions").toList().map { it as String },
            SubUsersActions(server, baseRequest,json.getString("uuid")))
    }

}