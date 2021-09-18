package br.com.lighthost.kotlinDactylApi.impl.client.account.models

import br.com.lighthost.kotlinDactylApi.impl.client.account.actions.ClientApiKeysActions
import java.time.OffsetDateTime

data class ClientApiKeyModel(
    val identifier:String,
    val description:String,
    val allowedIps:List<String>,
    val lastUsed:OffsetDateTime?,
    val createdAt:OffsetDateTime,
    val actions:ClientApiKeysActions)
