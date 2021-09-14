package br.com.lighthost.kotlinDactylApi.client.server.managers.backups.models

import br.com.lighthost.kotlinDactylApi.client.server.managers.backups.actions.ClientBackupActions
import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import java.time.OffsetDateTime

data class ClientBackupModel(
    val uuid:String,
    val isSuccessful:Boolean,
    val isLocked:Boolean,
    val name:String,
    val ignoredFiles:List<String>?,
    val checksum: String?,
    val bytes:Long,
    val createdAt:OffsetDateTime,
    val completedAt: OffsetDateTime?,
    private val server:ClientServerDetails,
    private val baseRequest: BaseRequest){

    fun actions(): ClientBackupActions {
        return ClientBackupActions(server, baseRequest, uuid)
    }

}
