package br.com.lighthost.kotlinDactylApi.impl.application.servers.models

data class ApplicationServerAllocationModel(
    val id:Int,
    val ip:String,
    val alias:String,
    val port:Long,
    val notes:String?,
    val isAssigned:Boolean)

