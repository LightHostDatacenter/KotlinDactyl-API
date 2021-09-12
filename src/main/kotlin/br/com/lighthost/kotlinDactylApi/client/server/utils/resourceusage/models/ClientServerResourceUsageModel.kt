package br.com.lighthost.kotlinDactylApi.client.server.utils.resourceusage.models

data class ClientServerResourceUsageModel(
    val isSuspended:Boolean,
    val memoryBytes:Long,
    val cpuAbsolute:Double,
    val networkRx:Long,
    val networkTx:Long,
    val state:String,
    val diskBytes:Long)
