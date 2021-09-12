package br.com.lighthost.kotlinDactylApi.client.console.models

data class ConsoleBackupModel(
    val checksum:String,
    val checksumType:String,
    val fileSize:Long,
    val isSuccessful:Boolean,
    val uuid:String)

