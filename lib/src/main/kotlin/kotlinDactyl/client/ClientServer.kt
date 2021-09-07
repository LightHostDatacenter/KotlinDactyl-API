package kotlinDactyl.client

import kotlinDactyl.client.databases.ClientDatabaseManager
import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.client.files.ClientFileManager
import kotlinDactyl.client.network.ClientNetworkManager
import kotlinDactyl.requests.BaseRequest
import org.json.JSONObject

class ClientServer (jsonResponse:String, baseRequest:BaseRequest) {

    private val server = ClientServerDetails(JSONObject(jsonResponse).getJSONObject("attributes"))

    val details: ClientServerDetails = server
    val fileManager = ClientFileManager(server, baseRequest)
    val databaseManager = ClientDatabaseManager(server, baseRequest)
    val networkManager = ClientNetworkManager(server, baseRequest)

}