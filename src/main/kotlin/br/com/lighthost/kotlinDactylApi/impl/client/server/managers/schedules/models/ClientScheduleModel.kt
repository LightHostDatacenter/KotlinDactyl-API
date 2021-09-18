package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.schedules.models

import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.schedules.actions.ScheduleActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import java.time.OffsetDateTime

data class ClientScheduleModel(
    val id:Int,
    var name:String,
    var isActive:Boolean,
    var onlyWhenOnline:Boolean,
    var cron:ClientCronJobModel,
    val isProcessing:Boolean,
    val lastRun:OffsetDateTime?,
    val nextRun:OffsetDateTime,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime,
    val tasks:List<ClientTaskModel>?,
    private val baseRequest: BaseRequest,
    private val serverDetails: ClientServerDetails){

    fun actions(): ScheduleActions {
        return ScheduleActions(serverDetails, baseRequest, this)
    }

}
