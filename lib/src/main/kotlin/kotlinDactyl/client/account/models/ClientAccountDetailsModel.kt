package kotlinDactyl.client.account.models

data class ClientAccountDetailsModel(
    val id:Int,
    val isAdmin:Boolean,
    val username:String,
    val email:String,
    val firstName:String,
    val lastName:String,
    val language:String)
