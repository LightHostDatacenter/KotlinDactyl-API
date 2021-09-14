package br.com.lighthost.kotlinDactylApi.client.server.managers.subusers.models

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.subusers.actions.SubUsersActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import java.time.OffsetDateTime

data class ClientSubUserModel(
    val uuid:String,
    val username:String,
    val email:String,
    val image:String,
    val twoFactorEnabled:Boolean,
    val createdAt:OffsetDateTime,
    var permissions:MutableList<String>,
    private val baseRequest: BaseRequest,
    private val server:ClientServerDetails){

    fun actions(): SubUsersActions {
        return SubUsersActions(server, baseRequest, this)
    }

}
