package br.com.lighthost.kotlinDactylApi.impl.application.servers.models

import br.com.lighthost.kotlinDactylApi.impl.application.servers.ApplicationServerDetails
import br.com.lighthost.kotlinDactylApi.impl.application.servers.actions.ApplicationServerDetailsActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest

data class ApplicationServerDetailsModel(
    val id:Int,
    var name:String,
    var description:String?,
    var owner:Int,
    var externalId:String?,
    val uuid:String,
    val isSuspended:Boolean,
    val status:String?,
    val nest:Int,
    val installed:Int,
    private val baseRequest: BaseRequest,
    private val server: ApplicationServerDetails){

    fun actions(): ApplicationServerDetailsActions {
        return ApplicationServerDetailsActions(baseRequest, server)
    }

}
