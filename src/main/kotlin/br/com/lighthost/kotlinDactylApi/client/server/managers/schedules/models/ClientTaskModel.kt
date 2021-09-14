package br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.models

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.actions.TaskActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import java.time.OffsetDateTime

data class ClientTaskModel(
    val id:Int,
    var sequenceId:Int,
    var action:String,
    var payload:String,
    var timeOffset:Int,
    var continueOnFailure:Boolean,
    val isQueued:Boolean,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime?,
    private val scheduleId:Int,
    private val baseRequest: BaseRequest,
    private val server:ClientServerDetails){

    fun actions(): TaskActions {
        return TaskActions(server, baseRequest, this, scheduleId)
    }

}
