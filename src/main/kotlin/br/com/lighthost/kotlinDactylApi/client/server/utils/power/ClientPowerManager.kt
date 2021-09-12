package br.com.lighthost.kotlinDactylApi.client.server.utils.power

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ClientPowerManager (private val server : ClientServerDetails, private val baseRequest: BaseRequest) {

    fun start(){
        val json = JSONObject().accumulate("signal", "start")
        baseRequest.executeRequest(ClientRoutes.POWER.setServerPower(server.attributes.identifier), json.toString())
    }

    fun stop(){
        val json = JSONObject().accumulate("signal", "stop")
        baseRequest.executeRequest(ClientRoutes.POWER.setServerPower(server.attributes.identifier), json.toString())
    }

    fun restart(){
        val json = JSONObject().accumulate("signal", "restart")
        baseRequest.executeRequest(ClientRoutes.POWER.setServerPower(server.attributes.identifier), json.toString())
    }

    fun kill(){
        val json = JSONObject().accumulate("signal", "kill")
        baseRequest.executeRequest(ClientRoutes.POWER.setServerPower(server.attributes.identifier), json.toString())
    }

}