package br.com.lighthost.kotlinDactylApi.client.server.managers.details.models

data class ClientServerAttributesModel(
    val isSuspended:Boolean,
    val isInstalling:Boolean,
    val isOwner:Boolean,
    val identifier:String,
    val uuid:String,
    val name:String,
    val node:String,
    val description:String,
    val internalId:Int,
    val permissions:List<String>?)
