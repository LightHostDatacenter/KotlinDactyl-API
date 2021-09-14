package br.com.lighthost.kotlinDactylApi.client.server.managers.network.models

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.network.actions.ClientNetworkActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest

data class ClientAllocationModel(
    val id:Int,
    val ip:String,
    val ipAlias:String,
    val port:Int,
    var notes:String?,
    val isPrimary:Boolean,
    private val server:ClientServerDetails,
    private val baseRequest: BaseRequest){

    fun actions(): ClientNetworkActions {
        return ClientNetworkActions(server, baseRequest, this)
    }

}
