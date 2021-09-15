package br.com.lighthost.kotlinDactylApi.application.nodes.models

data class ApplicationNewNodeModel(
    val name:String,
    val locationId:Int,
    val fqdn:String,
    val scheme:String,
    val memory:Long,
    val memoryOverAllocate:Long,
    val disk:Long,
    val diskOverAllocate:Long,
    val uploadSize:Long,
    val daemonSftpPort:Long,
    val daemonListenPort:Long,
    val isPublic:Boolean,
    val description:String,
    val isBehindProxy:Boolean,
    val daemonBase:String)
