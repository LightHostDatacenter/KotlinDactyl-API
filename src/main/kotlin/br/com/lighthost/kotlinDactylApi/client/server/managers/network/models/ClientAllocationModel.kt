package br.com.lighthost.kotlinDactylApi.client.server.managers.network.models

import br.com.lighthost.kotlinDactylApi.client.server.managers.network.actions.ClientNetworkActions

data class ClientAllocationModel(
    val id:Int,
    val ip:String,
    val ipAlias:String,
    val port:Int,
    val notes:String?,
    val isPrimary:Boolean,
    val actions:ClientNetworkActions)
