package kotlinDactyl.client.databases

import kotlinDactyl.client.ClientServer
import kotlinDactyl.client.databases.models.ClientDatabaseModel
import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.client.files.models.ClientFileModel
import kotlinDactyl.requests.BaseRequest
import kotlinDactyl.requests.RouteModels.ClientRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

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
                json.getInt("max_connections"),
                json.getJSONObject("relationships").getJSONObject("password").getJSONObject("attributes").getString("password"))
        }
    }

}