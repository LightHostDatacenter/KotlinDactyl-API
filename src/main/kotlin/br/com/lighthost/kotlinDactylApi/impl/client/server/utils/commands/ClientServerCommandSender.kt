package br.com.lighthost.kotlinDactylApi.impl.client.server.utils.commands

import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ClientServerCommandSender (private val baseRequest:BaseRequest, private val server: ClientServerDetails) {

    fun sendCommand(command:String){
        val json = JSONObject().accumulate("command", command)
        baseRequest.executeRequest(ClientRoutes.SERVER.sendCommand(server.attributes.identifier), json.toString())
    }

}