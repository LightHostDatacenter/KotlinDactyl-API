package br.com.lighthost.kotlinDactylApi.client.network.actions

import br.com.lighthost.kotlinDactylApi.client.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientNetworkActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val allocationId:Int) {

    fun setAllocationNote(note:String) {
        val json = JSONObject().accumulate("notes", note)
        baseRequest.executeRequest(ClientRoutes.NETWORK.setAllocationNote(server.attributes.identifier, allocationId), json.toString())
    }

    fun makePrimary() {
        baseRequest.executeRequest(ClientRoutes.NETWORK.setPrimaryAllocation(server.attributes.identifier, allocationId), null)
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.NETWORK.deleteAllocation(server.attributes.identifier, allocationId), null)
    }

}