package kotlinDactyl.client.subusers.models

import kotlinDactyl.client.subusers.actions.SubUsersActions
import java.time.OffsetDateTime

data class ClientSubUserModel(
    val uuid:String,
    val username:String,
    val email:String,
    val image:String,
    val twoFactorEnabled:Boolean,
    val createdAt:OffsetDateTime,
    val permissions:List<String>,
    val actions: SubUsersActions)
