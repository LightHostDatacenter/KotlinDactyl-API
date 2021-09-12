package br.com.lighthost.kotlinDactylApi.client.server.managers.files.models

import java.time.OffsetDateTime

data class ClientFileModel(
    val name:String,
    val mode:String,
    val modeBits:String,
    val size:Long,
    val isFile:Boolean,
    val isSymlink:Boolean,
    val mimeType:String,
    val createdAt:OffsetDateTime,
    val modifiedAt:OffsetDateTime)
