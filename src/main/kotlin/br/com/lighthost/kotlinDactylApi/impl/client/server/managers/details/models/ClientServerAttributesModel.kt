package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.details.models

data class ClientServerAttributesModel(
    val isSuspended:Boolean,
    val isInstalling:Boolean,
    val isOwner:Boolean,
    val identifier:String,
    val uuid:String,
    val name:String,
    val eggName:String,
    val currentImage:String,
    val startupCommand:String,
    val node:String,
    val description:String,
    val internalId:Int,
    val permissions:List<String>?)
