package br.com.lighthost.kotlinDactylApi.application.nodes.models

import br.com.lighthost.kotlinDactylApi.application.nodes.actions.ApplicationAllocationActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest

data class ApplicationAllocationModel(
    val id:Int,
    val ip:String,
    val alias:String,
    val port:Long,
    val notes:String?,
    val isAssigned:Boolean,
    private val baseRequest: BaseRequest,
    private val nodeId:Int){

    fun actions(): ApplicationAllocationActions {
        return ApplicationAllocationActions(baseRequest, nodeId, this)
    }
}


