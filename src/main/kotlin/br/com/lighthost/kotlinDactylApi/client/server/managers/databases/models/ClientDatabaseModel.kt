package br.com.lighthost.kotlinDactylApi.client.server.managers.databases.models

import br.com.lighthost.kotlinDactylApi.client.server.managers.databases.actions.ClientDatabaseActions

data class ClientDatabaseModel(
    val id:String,
    val address:String,
    val port:Int,
    val name:String,
    val username:String,
    val allowedRemote:String,
    val password:String,
    val actions: ClientDatabaseActions)
