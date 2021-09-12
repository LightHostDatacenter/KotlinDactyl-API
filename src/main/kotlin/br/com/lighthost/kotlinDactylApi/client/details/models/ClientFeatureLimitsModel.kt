package br.com.lighthost.kotlinDactylApi.client.details.models

data class ClientFeatureLimitsModel(
    val databases:Int,
    val allocations:Int,
    val backups:Int)
