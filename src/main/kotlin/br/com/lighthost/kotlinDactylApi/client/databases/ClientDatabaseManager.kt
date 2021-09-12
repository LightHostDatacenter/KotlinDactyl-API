package br.com.lighthost.kotlinDactylApi.client.databases

import br.com.lighthost.kotlinDactylApi.client.databases.actions.ClientDatabaseActions
import br.com.lighthost.kotlinDactylApi.client.databases.models.ClientDatabaseModel
import br.com.lighthost.kotlinDactylApi.client.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientDatabaseManager (private val server : ClientServerDetails, private val baseRequest: BaseRequest) {

    fun retrieveDatabases():List<ClientDatabaseModel>{
        val list:MutableList<ClientDatabaseModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.DATABASES.listDatabases(server.attributes.identifier), null))
            .getJSONArray("data").forEach{
                it as JSONObject
                list.add(clientDatabaseParser(it.getJSONObject("attributes").toString()))
            }
        return list
    }

    fun retrieveDatabase(id:String): ClientDatabaseModel {
        return retrieveDatabases().first { it.id == id }
    }

    fun createDatabase(name:String, allowedNetwork:String): ClientDatabaseModel{
        val json = JSONObject().accumulate("database", name).accumulate("remote", allowedNetwork)
        return clientDatabaseParser(JSONObject
            (baseRequest.executeRequest(ClientRoutes.DATABASES.createDatabase(server.attributes.identifier),
            json.toString())).getJSONObject("attributes").toString())
    }

    private fun clientDatabaseParser(rawJson : String): ClientDatabaseModel {
        val json = JSONObject(rawJson)
            return ClientDatabaseModel(
                json.getString("id"),
                json.getJSONObject("host").getString("address"),
                json.getJSONObject("host").getInt("port"),
                json.getString("name"),
                json.getString("username"),
                json.getString("connections_from"),
                json.getJSONObject("relationships").getJSONObject("password").getJSONObject("attributes").getString("password"),
                ClientDatabaseActions(server, baseRequest, json.getString("id")))
        }

}