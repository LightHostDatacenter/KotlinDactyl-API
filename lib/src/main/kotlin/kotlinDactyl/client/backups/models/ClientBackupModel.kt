package kotlinDactyl.client.backups.models

import java.time.OffsetDateTime
import kotlinDactyl.helpers.DataTransformers.SizeTransform

data class ClientBackupModel(
    val uuid:String,
    val isSuccessful:Boolean,
    val isLocked:Boolean,
    val name:String,
    val ignoredFiles:List<String>?,
    val checksum: String?,
    val bytes:Long,
    val createdAt:OffsetDateTime,
    val completedAt: OffsetDateTime?)
