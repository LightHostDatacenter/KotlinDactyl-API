package br.com.lighthost.kotlinDactylApi.application.nodes.models

import br.com.lighthost.kotlinDactylApi.application.nodes.actions.ApplicationNodeActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import java.time.OffsetDateTime

data class ApplicationNodeModel(
    val id:Int,
    val uuid:String,
    var isPublic:Boolean,
    var name:String,
    var description:String?,
    var locationId:Int,
    var fqdn:String,
    var scheme:String,
    var isBehindProxy:Boolean,
    var isMaintenanceMode:Boolean,
    var memory:Long,
    var memoryOverAllocate: Long,
    var disk:Long,
    var diskOverAllocate:Long,
    val uploadSize:Long,
    var daemonListenPort:Long,
    var daemonSftpPort:Long,
    val daemonBase:String,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime,
    val allocatedMemory:Long,
    val allocatedDisk:Long,
    val servers:List<Int>,
    private val baseRequest: BaseRequest){

    fun actions(): ApplicationNodeActions {
        return ApplicationNodeActions(baseRequest, this)
    }

}
