package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.console.models

data class ConsoleStatsModel(
    val memoryBytes:Long,
    val memoryLimitBytes:Long,
    val cpuAbsolute:Double,
    val networkRx:Long,
    val networkTx:Long,
    val state:String,
    val diskBytes:Long)
