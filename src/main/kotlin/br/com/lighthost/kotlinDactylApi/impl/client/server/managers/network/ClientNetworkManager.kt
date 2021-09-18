package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.network

import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.network.models.ClientAllocationModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ClientNetworkManager(private val server: ClientServerDetails, private val baseRequest: BaseRequest) {

    fun retrieveAllocations(): List<ClientAllocationModel> {
        val list:MutableList<ClientAllocationModel> = mutableListOf()
        val allocationsArray = JSONObject(baseRequest.executeRequest(ClientRoutes.NETWORK.listAllocations(server.attributes.identifier), null)).getJSONArray("data")
        for (i:Int in 0 until allocationsArray.length()){
            val it = allocationsArray.getJSONObject(i)
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
                server,
                baseRequest)
        }

}