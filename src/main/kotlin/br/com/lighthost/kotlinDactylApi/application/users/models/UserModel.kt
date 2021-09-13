package br.com.lighthost.kotlinDactylApi.application.users.models

import br.com.lighthost.kotlinDactylApi.application.users.actions.ApplicationUsersActions
import java.time.OffsetDateTime

data class UserModel(
    val id:Int,
    val externalId:String?,
    val uuid:String,
    val username:String,
    val email:String,
    val firstName:String,
    val lastName:String,
    val language:String,
    val isRootAdmin:Boolean,
    val is2FA:Boolean,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime,
    val userUpdateModel:UserUpdateModel,
    val actions:ApplicationUsersActions)
