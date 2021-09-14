package br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.actions

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.models.ClientTaskModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class TaskActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val task:ClientTaskModel, private val scheduleId:Int){

    fun update() {
        val json = JSONObject().accumulate("action", task.action)
            .accumulate("payload", task.payload)
            .accumulate("time_offset", task.timeOffset)
            .accumulate("continue_on_failure", task.continueOnFailure)
            .accumulate("sequence_id", task.sequenceId)
            baseRequest.executeRequest(
                ClientRoutes.SCHEDULES.updateTask(server.attributes.identifier, scheduleId, task.id),
                json.toString())
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.SCHEDULES.deleteTask(server.attributes.identifier, scheduleId, task.id), null)
    }

}