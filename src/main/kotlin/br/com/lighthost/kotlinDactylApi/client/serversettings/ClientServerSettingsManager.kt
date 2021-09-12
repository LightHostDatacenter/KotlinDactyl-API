package br.com.lighthost.kotlinDactylApi.client.serversettings

import br.com.lighthost.kotlinDactylApi.client.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientServerSettingsManager(private val server : ClientServerDetails, private val baseRequest: BaseRequest) {

    fun renameServer(name:String){
        val json = JSONObject().accumulate("name", name)
        baseRequest.executeRequest(ClientRoutes.SETTINGS.renameServer(server.attributes.identifier), json.toString())
    }

    fun reinstallServer(){
        baseRequest.executeRequest(ClientRoutes.SETTINGS.reinstallServer(server.attributes.identifier), "")
    }

}