package br.com.lighthost.kotlinDactylApi.impl.application.users.models

import br.com.lighthost.kotlinDactylApi.impl.application.users.actions.ApplicationUsersActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import java.time.OffsetDateTime

data class ApplicationUserModel(
    val id:Int,
    val uuid:String,
    var externalId:String?,
    var username:String,
    var email:String,
    var firstName:String,
    var lastName:String,
    var language:String,
    var isRootAdmin:Boolean,
    val is2FA:Boolean,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime,
    private val baseRequest: BaseRequest){

    fun actions(): ApplicationUsersActions {
        return ApplicationUsersActions(baseRequest,this)
    }

}
