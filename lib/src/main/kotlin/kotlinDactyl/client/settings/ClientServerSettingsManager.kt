package kotlinDactyl.client.settings

import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.requests.BaseRequest
import kotlinDactyl.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientServerSettingsManager(private val server : ClientServerDetails, private val baseRequest: BaseRequest) {

    fun renameServer(name:String){
        val json = JSONObject().accumulate("name", name)
        baseRequest.executeRequest(ClientRoutes.SETTINGS.renameServer(server.identifier), json.toString())
    }

    fun reinstallServer(){
        baseRequest.executeRequest(ClientRoutes.SETTINGS.reinstallServer(server.identifier), "")
    }

}