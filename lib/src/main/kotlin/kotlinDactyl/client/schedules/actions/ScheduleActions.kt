package kotlinDactyl.client.schedules.actions

import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.client.schedules.ClientScheduleManager
import kotlinDactyl.client.schedules.models.ClientTaskModel
import kotlinDactyl.client.schedules.models.NewScheduleModel
import kotlinDactyl.client.schedules.models.NewTaskModel
import kotlinDactyl.requests.BaseRequest
import kotlinDactyl.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ScheduleActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val scheduleId:Int){

    fun createTask(task:NewTaskModel): ClientTaskModel {
        val json = JSONObject().accumulate("action", task.action)
            .accumulate("payload", task.payload)
            .accumulate("time_offset", task.timeOffset)
            .accumulate("continue_on_failure", task.continueOnFailure)
        return ClientScheduleManager(server, baseRequest).parseClientTask(JSONObject(
            baseRequest.executeRequest(ClientRoutes.SCHEDULES.createTask(server.identifier, scheduleId),
                json.toString())).getJSONObject("attributes").toString(), scheduleId)
    }

    fun update(updateModel:NewScheduleModel){
        val json = JSONObject().accumulate("name", updateModel.name)
            .accumulate("is_active", updateModel.isActive)
            .accumulate("minute", updateModel.cron.minute)
            .accumulate("hour", updateModel.cron.hour)
            .accumulate("day_of_week", updateModel.cron.dayOfWeek)
            .accumulate("month", updateModel.cron.month)
            .accumulate("day_of_month", updateModel.cron.dayOfMonth)
            .accumulate("only_when_online", updateModel.onlyWhenOnline)
        baseRequest.executeRequest(ClientRoutes.SCHEDULES.updateSchedule(server.identifier, scheduleId),json.toString())
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.SCHEDULES.deleteSchedule(server.identifier, scheduleId), null)
    }

}