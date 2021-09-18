package br.com.lighthost.kotlinDactylApi.impl.common.models.servers

data class ServerFeatureLimitsModel(
    var databases:Int,
    var allocations:Int,
    var backups:Int)
