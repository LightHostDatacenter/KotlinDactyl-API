package kotlinDactyl.client

import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.client.files.ClientServerFileManager
import kotlinDactyl.requests.BaseRequest
import org.json.JSONObject

class ClientServer (jsonResponse:String, baseRequest:BaseRequest) {

    private val server = ClientServerDetails(JSONObject(jsonResponse).getJSONObject("attributes"))

    val details: ClientServerDetails = server
    val fileManager = ClientServerFileManager(server, baseRequest)

}