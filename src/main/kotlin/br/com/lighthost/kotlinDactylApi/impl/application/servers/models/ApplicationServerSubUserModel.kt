package br.com.lighthost.kotlinDactylApi.impl.application.servers.models

import java.time.OffsetDateTime

data class ApplicationServerSubUserModel(
    val id:Int,
    val userId:Int,
    val serverId:Int,
    val permissions:List<String>,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime)
