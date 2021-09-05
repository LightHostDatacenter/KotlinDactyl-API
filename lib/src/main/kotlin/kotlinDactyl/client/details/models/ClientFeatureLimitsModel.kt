package kotlinDactyl.client.details.models

data class ClientFeatureLimitsModel(
    val databases:Int,
    val allocations:Int,
    val backups:Int)
