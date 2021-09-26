package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.files.models

import java.time.OffsetDateTime

data class ClientFileModel(
    val name:String,
    val mode:String,
    val modeBits:String,
    val size:Long,
    val sizeFormatted:String,
    val isFile:Boolean,
    val isEditable:Boolean,
    val editMode:String?,
    val isSymlink:Boolean,
    val mimeType:String,
    val createdAt:OffsetDateTime,
    val modifiedAt:OffsetDateTime){
}
