package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.network.actions

import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.network.models.ClientAllocationModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ClientNetworkActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val allocation:ClientAllocationModel) {

    fun update() {
        val json = JSONObject().accumulate("notes", allocation.notes)
        baseRequest.executeRequest(ClientRoutes.NETWORK.setAllocationNote(server.attributes.identifier, allocation.id), json.toString())
    }

    fun makePrimary() {
        baseRequest.executeRequest(ClientRoutes.NETWORK.setPrimaryAllocation(server.attributes.identifier, allocation.id), null)
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.NETWORK.deleteAllocation(server.attributes.identifier, allocation.id), null)
    }

}