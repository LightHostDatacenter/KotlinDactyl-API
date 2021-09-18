package br.com.lighthost.kotlinDactylApi.impl.application.servers.models

import br.com.lighthost.kotlinDactylApi.impl.common.models.servers.ServerFeatureLimitsModel
import br.com.lighthost.kotlinDactylApi.impl.common.models.servers.ServerLimitsModel

data class ApplicationNewServerModel(
    val docker_image: String,
    val egg: Int,
    val startup: String,
    val name: String,
    val description: String?,
    val externalId:String?,
    val user: Int,
    val primaryAllocation:Int?,
    val envs: Map<String, String>,
    val featureLimits: ServerFeatureLimitsModel,
    val limits: ServerLimitsModel)