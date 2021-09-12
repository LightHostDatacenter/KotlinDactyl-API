package br.com.lighthost.kotlinDactylApi.client.network

import br.com.lighthost.kotlinDactylApi.client.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.network.models.ClientAllocationModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientNetworkManager(private val server: ClientServerDetails, private val baseRequest: BaseRequest) {

    fun getAllocations(): List<ClientAllocationModel> {
        val list:MutableList<ClientAllocationModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.NETWORK.listAllocations(server.identifier), null)).getJSONArray("data").forEach {
            it as JSONObject
            list.add(ClientNetworkManagerParser.parse(it.getJSONObject("attributes").toString()))
        }
        return list
    }

    fun getAllocation(id:Int): ClientAllocationModel {
        return getAllocations().first{it.id == id}
    }

    fun assignNewAllocation(): ClientAllocationModel {
        return ClientNetworkManagerParser.parse(JSONObject(baseRequest.executeRequest(ClientRoutes.NETWORK.assignAllocation(server.identifier), ""))
            .getJSONObject("attributes").toString())
    }

    fun setAllocationNote(id:Int, note:String): ClientAllocationModel {
        val json = JSONObject().accumulate("notes", note)
        return ClientNetworkManagerParser.parse(JSONObject(
            baseRequest.executeRequest(ClientRoutes.NETWORK.setAllocationNote(server.identifier, id), json.toString())
        ).getJSONObject("attributes").toString())
    }

    fun setPrimaryAllocation(id:Int):ClientAllocationModel{
        return ClientNetworkManagerParser.parse(JSONObject(baseRequest.executeRequest(ClientRoutes.NETWORK.setPrimaryAllocation(server.identifier, id), ""))
            .getJSONObject("attributes").toString())
    }

    fun deleteAllocation(id:Int){
        baseRequest.executeRequest(ClientRoutes.NETWORK.deleteAllocation(server.identifier, id), null)
    }

    object ClientNetworkManagerParser {
        fun parse(rawJson : String): ClientAllocationModel {
            val json = JSONObject(rawJson)
            return ClientAllocationModel(
                json.getInt("id"),
                json.getString("ip"),
                json.get("ip_alias").toString(),
                json.getInt("port"),
                json.get("notes").toString(),
                json.getBoolean("is_default"))
        }
    }

}