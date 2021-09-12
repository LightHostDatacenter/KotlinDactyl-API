package br.com.lighthost.kotlinDactylApi.client.account.models

import java.time.OffsetDateTime

data class ClientApiKeyModel(
    val identifier:String,
    val description:String,
    val allowedIps:List<String>,
    val lastUsed:OffsetDateTime?,
    val createdAt:OffsetDateTime)
