package br.com.lighthost.kotlinDactylApi.client.network.models

import br.com.lighthost.kotlinDactylApi.client.network.actions.ClientNetworkActions

data class ClientAllocationModel(
    val id:Int,
    val ip:String,
    val ipAlias:String,
    val port:Int,
    val notes:String?,
    val isPrimary:Boolean,
    val actions:ClientNetworkActions)
