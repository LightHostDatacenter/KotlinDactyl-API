package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.startup.actions

import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ClientStartupActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val envVariable: String) {

    fun updateVariable(newValue:String) {
        val json = JSONObject().accumulate("key", envVariable).accumulate("value", newValue)
        baseRequest.executeRequest(ClientRoutes.STARTUP.updateVariable(server.attributes.identifier),json.toString())
    }

}