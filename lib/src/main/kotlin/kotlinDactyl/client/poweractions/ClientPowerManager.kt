package kotlinDactyl.client.poweractions

import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.requests.BaseRequest
import kotlinDactyl.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientPowerManager (private val server : ClientServerDetails, private val baseRequest: BaseRequest) {

    fun start(){
        val json = JSONObject().accumulate("signal", "start")
        baseRequest.executeRequest(ClientRoutes.POWER.setServerPower(server.identifier), json.toString())
    }

    fun stop(){
        val json = JSONObject().accumulate("signal", "stop")
        baseRequest.executeRequest(ClientRoutes.POWER.setServerPower(server.identifier), json.toString())
    }

    fun restart(){
        val json = JSONObject().accumulate("signal", "restart")
        baseRequest.executeRequest(ClientRoutes.POWER.setServerPower(server.identifier), json.toString())
    }

    fun kill(){
        val json = JSONObject().accumulate("signal", "kill")
        baseRequest.executeRequest(ClientRoutes.POWER.setServerPower(server.identifier), json.toString())
    }

}