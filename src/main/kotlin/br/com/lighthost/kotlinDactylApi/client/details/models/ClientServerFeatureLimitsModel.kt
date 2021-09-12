package br.com.lighthost.kotlinDactylApi.client.details.models

data class ClientServerFeatureLimitsModel(
    val databases:Int,
    val allocations:Int,
    val backups:Int)
