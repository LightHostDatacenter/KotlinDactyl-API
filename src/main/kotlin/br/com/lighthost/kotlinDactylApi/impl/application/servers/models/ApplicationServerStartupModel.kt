package br.com.lighthost.kotlinDactylApi.impl.application.servers.models

import br.com.lighthost.kotlinDactylApi.impl.application.servers.ApplicationServerDetails
import br.com.lighthost.kotlinDactylApi.impl.application.servers.actions.ApplicationServerStartupActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest

data class ApplicationServerStartupModel(
    var startup:String,
    var environment:Map<String, String>?,
    var egg:Int,
    var image:String,
    var skipScripts:Boolean,
    private val baseRequest: BaseRequest,
    private val server: ApplicationServerDetails){

    fun actions(): ApplicationServerStartupActions {
        return ApplicationServerStartupActions(baseRequest, server)
    }

}
