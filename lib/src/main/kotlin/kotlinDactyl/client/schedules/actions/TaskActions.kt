package kotlinDactyl.client.schedules.actions

import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.client.schedules.ClientScheduleManager
import kotlinDactyl.client.schedules.models.ClientTaskModel
import kotlinDactyl.client.schedules.models.NewTaskModel
import kotlinDactyl.requests.BaseRequest
import kotlinDactyl.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class TaskActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val taskId:Int, private val scheduleId:Int){

    fun update(taskUpdate:NewTaskModel): ClientTaskModel {
        val json = JSONObject().accumulate("action", taskUpdate.action)
            .accumulate("payload", taskUpdate.payload)
            .accumulate("time_offset", taskUpdate.timeOffset)
            .accumulate("continue_on_failure", taskUpdate.continueOnFailure)
        return ClientScheduleManager(server, baseRequest).parseClientTask(
            JSONObject(baseRequest.executeRequest(ClientRoutes.SCHEDULES.updateTask(server.identifier, scheduleId, taskId),
                json.toString())).getJSONObject("attributes").toString(), scheduleId)
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.SCHEDULES.deleteTask(server.identifier, scheduleId, taskId), null)
    }

}