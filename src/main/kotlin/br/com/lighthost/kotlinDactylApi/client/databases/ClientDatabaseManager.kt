package br.com.lighthost.kotlinDactylApi.client.databases

import br.com.lighthost.kotlinDactylApi.client.databases.models.ClientDatabaseModel
import br.com.lighthost.kotlinDactylApi.client.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientDatabaseManager (private val server : ClientServerDetails, private val baseRequest: BaseRequest) {

    fun getDatabases():List<ClientDatabaseModel>{
        val list:MutableList<ClientDatabaseModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.DATABASES.listDatabases(server.identifier), null))
            .getJSONArray("data").forEach{
                it as JSONObject
                list.add(ClientDatabaseParser.parse(it.getJSONObject("attributes").toString()))
            }
        return list
    }

    fun getDatabase(id:String): ClientDatabaseModel {
        return getDatabases().first { it.id == id }
    }

    fun createDatabase(name:String, allowedNetwork:String): ClientDatabaseModel{
        val json = JSONObject().accumulate("database", name).accumulate("remote", allowedNetwork)
        return ClientDatabaseParser.parse(JSONObject
            (baseRequest.executeRequest(ClientRoutes.DATABASES.createDatabase(server.identifier),
            json.toString())).getJSONObject("attributes").toString())
    }

    fun rotatePassword(id:String):ClientDatabaseModel{
        return ClientDatabaseParser.parse(JSONObject
            (baseRequest.executeRequest(ClientRoutes.DATABASES.rotatePassword(server.identifier, id),"")).getJSONObject("attributes").toString())
    }

    fun deleteDatabase(id:String){
        baseRequest.executeRequest(ClientRoutes.DATABASES.deleteDatabase(server.identifier, id), null)
    }

    object ClientDatabaseParser {
        fun parse(rawJson : String): ClientDatabaseModel {
            val json = JSONObject(rawJson)
            return ClientDatabaseModel(
                json.getString("id"),
                json.getJSONObject("host").getString("address"),
                json.getJSONObject("host").getInt("port"),
                json.getString("name"),
                json.getString("username"),
                json.getString("connections_from"),
                json.getJSONObject("relationships").getJSONObject("password").getJSONObject("attributes").getString("password"))
        }
    }

}