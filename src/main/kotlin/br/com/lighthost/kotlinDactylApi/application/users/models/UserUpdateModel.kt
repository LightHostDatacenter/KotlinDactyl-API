package br.com.lighthost.kotlinDactylApi.application.users.models

data class UserUpdateModel(
    var email:String,
    var username:String,
    var firstName:String,
    var lastName:String,
    var externalId:String?,
    var isRootAdmin:Boolean,
    var language:String?)
