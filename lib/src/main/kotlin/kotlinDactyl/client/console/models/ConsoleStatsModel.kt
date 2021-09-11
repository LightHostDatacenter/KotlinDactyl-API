package kotlinDactyl.client.console.models

import kotlinDactyl.helpers.DataTransformers.SizeTransform

data class ConsoleStatsModel(
    val memoryBytes:Long,
    val memoryLimitBytes:Long,
    val cpuAbsolute:Double,
    val networkRx:Long,
    val networkTx:Long,
    val state:String,
    val diskBytes:Long)
