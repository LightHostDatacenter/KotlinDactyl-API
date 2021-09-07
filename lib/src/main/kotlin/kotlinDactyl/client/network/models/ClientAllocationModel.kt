package kotlinDactyl.client.network.models

data class ClientAllocationModel(
    val id:Int,
    val ip:String,
    val ipAlias:String,
    val port:Int,
    val notes:String?,
    val isDefault:Boolean)
