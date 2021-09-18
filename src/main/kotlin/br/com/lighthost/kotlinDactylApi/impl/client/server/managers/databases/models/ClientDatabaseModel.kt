package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.databases.models

import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.databases.actions.ClientDatabaseActions
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest

data class ClientDatabaseModel(
    val id:String,
    val address:String,
    val port:Int,
    val name:String,
    val username:String,
    val allowedRemote:String,
    val password:String,
    private val server:ClientServerDetails,
    private val baseRequest: BaseRequest){

    fun actions(): ClientDatabaseActions {
        return ClientDatabaseActions(server, baseRequest, id)
    }

}
