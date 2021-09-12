package br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.actions

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.ClientScheduleManager
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.models.ClientTaskModel
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.models.NewTaskModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class TaskActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val taskId:Int, private val scheduleId:Int){

    fun update(taskUpdate:NewTaskModel): ClientTaskModel {
        val json = JSONObject().accumulate("action", taskUpdate.action)
            .accumulate("payload", taskUpdate.payload)
            .accumulate("time_offset", taskUpdate.timeOffset)
            .accumulate("continue_on_failure", taskUpdate.continueOnFailure)
        return ClientScheduleManager(server, baseRequest).parseClientTask(
            JSONObject(baseRequest.executeRequest(
                ClientRoutes.SCHEDULES.updateTask(server.attributes.identifier, scheduleId, taskId),
                json.toString())).getJSONObject("attributes").toString(), scheduleId)
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.SCHEDULES.deleteTask(server.attributes.identifier, scheduleId, taskId), null)
    }

}