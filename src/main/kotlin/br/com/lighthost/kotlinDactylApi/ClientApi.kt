package br.com.lighthost.kotlinDactylApi

import br.com.lighthost.kotlinDactylApi.client.ClientServer
import br.com.lighthost.kotlinDactylApi.client.account.ClientAccountManager
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientApi (baseUrl:String, apiKey:String) {

    private val baseRequest = BaseRequest(baseUrl, apiKey)

    val user = ClientAccountManager(baseRequest)

    fun retrieveServerByIdentifier(id:String): ClientServer {
        return ClientServer(baseRequest.executeRequest(ClientRoutes.SERVER.getServer(id), null), baseRequest)
    }

    fun retrieveServers(): List<ClientServer>{
        val list:MutableList<ClientServer> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.SERVER.getServers(), null)).getJSONArray("data").forEach{
            it as JSONObject
            list.add(ClientServer(it.toString(), baseRequest))
        }
        return list
    }

}