package br.com.lighthost.kotlinDactylApi.impl.application.servers.models

import br.com.lighthost.kotlinDactylApi.impl.application.servers.ApplicationServerDetails
import br.com.lighthost.kotlinDactylApi.impl.application.servers.actions.ApplicationServerBuildActions
import br.com.lighthost.kotlinDactylApi.impl.common.models.servers.ServerFeatureLimitsModel
import br.com.lighthost.kotlinDactylApi.impl.common.models.servers.ServerLimitsModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest

data class ApplicationServerBuildModel(
    val primaryAllocation:Int,
    val limits: ServerLimitsModel,
    val featureLimits: ServerFeatureLimitsModel,
    private val baseRequest: BaseRequest,
    private val server: ApplicationServerDetails
){

    fun actions(): ApplicationServerBuildActions {
        return ApplicationServerBuildActions(baseRequest, server)
    }

}