package kotlinDactyl.client

import kotlinDactyl.client.details.ClientServerDetails
import org.json.JSONObject

class ClientServer (jsonResponse:String) {

    val details: ClientServerDetails = ClientServerDetails(JSONObject(jsonResponse).getJSONObject("attributes"))

}