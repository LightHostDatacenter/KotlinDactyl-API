package br.com.lighthost.kotlinDactylApi.api.client.models

data class SystemPermissionsModel(
    val name:String,
    val description:String,
    val keys:List<SystemPermissionsKeyModel>)
