package kotlinDactyl.client.details.models

data class ClientLimitsModel(
    val memory:Int,
    val swap:Int,
    val disk:Int,
    val io:Int,
    val cpu:Int)
