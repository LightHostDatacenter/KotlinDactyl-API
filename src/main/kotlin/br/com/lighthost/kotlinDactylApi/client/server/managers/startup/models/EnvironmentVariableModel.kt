package br.com.lighthost.kotlinDactylApi.client.server.managers.startup.models

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.startup.actions.ClientStartupActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest

data class EnvironmentVariableModel(
    val name:String,
    val description:String,
    val envVariable:String,
    val defaultValue:String,
    val serverValue:String,
    val isEditable:Boolean,
    val rules:String,
    private val server:ClientServerDetails,
    private val baseRequest: BaseRequest){

    fun actions(): ClientStartupActions {
        return ClientStartupActions(server, baseRequest, envVariable)
    }

}
