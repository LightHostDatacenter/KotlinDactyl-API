package br.com.lighthost.kotlinDactylApi.client.network

import br.com.lighthost.kotlinDactylApi.client.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.network.actions.ClientNetworkActions
import br.com.lighthost.kotlinDactylApi.client.network.models.ClientAllocationModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientNetworkManager(private val server: ClientServerDetails, private val baseRequest: BaseRequest) {

    fun retrieveAllocations(): List<ClientAllocationModel> {
        val list:MutableList<ClientAllocationModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.NETWORK.listAllocations(server.attributes.identifier), null)).getJSONArray("data").forEach {
            it as JSONObject
            list.add(clientNetworkManagerParser(it.getJSONObject("attributes").toString()))
        }
        return list
    }

    fun retrieveAllocation(id:Int): ClientAllocationModel {
        return retrieveAllocations().first{it.id == id}
    }

    fun assignNewAllocation(): ClientAllocationModel {
        return clientNetworkManagerParser(JSONObject(baseRequest.executeRequest(ClientRoutes.NETWORK.assignAllocation(server.attributes.identifier), ""))
            .getJSONObject("attributes").toString())
    }

    private fun clientNetworkManagerParser(rawJson : String): ClientAllocationModel {
            val json = JSONObject(rawJson)
            return ClientAllocationModel(
                json.getInt("id"),
                json.getString("ip"),
                json.get("ip_alias").toString(),
                json.getInt("port"),
                json.get("notes").toString(),
                json.getBoolean("is_default"),
                ClientNetworkActions(server, baseRequest, json.getInt("id")))
        }

}