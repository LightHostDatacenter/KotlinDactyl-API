package br.com.lighthost.kotlinDactylApi.impl.common.models.servers

data class ServerLimitsModel(
    var memory:Int,
    var swap:Int,
    var disk:Int,
    var io:Int,
    var cpu:Int)
