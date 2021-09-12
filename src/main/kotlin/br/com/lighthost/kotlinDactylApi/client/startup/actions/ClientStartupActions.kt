package br.com.lighthost.kotlinDactylApi.client.startup.actions

import br.com.lighthost.kotlinDactylApi.client.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientStartupActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val envVariable: String) {

    fun updateVariable(newValue:String) {
        val json = JSONObject().accumulate("key", envVariable).accumulate("value", newValue)
        baseRequest.executeRequest(ClientRoutes.STARTUP.updateVariable(server.attributes.identifier),json.toString())
    }

}