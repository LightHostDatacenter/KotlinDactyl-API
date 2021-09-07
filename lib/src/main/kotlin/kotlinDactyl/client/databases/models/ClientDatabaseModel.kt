package kotlinDactyl.client.databases.models

data class ClientDatabaseModel(
    val id:String,
    val address:String,
    val port:Int,
    val name:String,
    val username:String,
    val allowedRemote:String,
    val password:String)
