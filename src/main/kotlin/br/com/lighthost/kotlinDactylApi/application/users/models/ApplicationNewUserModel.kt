package br.com.lighthost.kotlinDactylApi.application.users.models

data class ApplicationNewUserModel(
    val email:String,
    val username:String,
    val firstName:String,
    val lastName:String,
    val externalId:String?,
    val isRootAdmin:Boolean,
    val language:String?)
