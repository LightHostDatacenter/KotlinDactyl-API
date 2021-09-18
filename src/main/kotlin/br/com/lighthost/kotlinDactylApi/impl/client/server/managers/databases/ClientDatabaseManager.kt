package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.databases

import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.databases.models.ClientDatabaseModel
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ClientDatabaseManager (private val server : ClientServerDetails, private val baseRequest: BaseRequest) {

    fun retrieveDatabases():List<ClientDatabaseModel>{
        val list:MutableList<ClientDatabaseModel> = mutableListOf()
        val databasesArray = JSONObject(baseRequest.executeRequest(ClientRoutes.DATABASES.listDatabases(server.attributes.identifier), null)).getJSONArray("data")
        for (i:Int in 0 until databasesArray.length()){
            val it = databasesArray.getJSONObject(i)
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
            (baseRequest.executeRequest(
            ClientRoutes.DATABASES.createDatabase(server.attributes.identifier),
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
                server,
                baseRequest)
        }

}