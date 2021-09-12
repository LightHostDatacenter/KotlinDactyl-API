package br.com.lighthost.kotlinDactylApi.client.databases.models

import br.com.lighthost.kotlinDactylApi.client.databases.actions.ClientDatabaseActions

data class ClientDatabaseModel(
    val id:String,
    val address:String,
    val port:Int,
    val name:String,
    val username:String,
    val allowedRemote:String,
    val password:String,
    val actions: ClientDatabaseActions)
