package kotlinDactyl.client.console.models

import kotlinDactyl.helpers.DataTransformers.SizeTransform

data class ConsoleBackupModel(
    val checksum:String,
    val checksumType:String,
    val fileSize:Long,
    val isSuccessful:Boolean,
    val uuid:String)

