package br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.actions

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.ClientScheduleManager
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.models.ClientTaskModel
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.models.NewScheduleModel
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.models.NewTaskModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ScheduleActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val scheduleId:Int){

    fun createTask(task:NewTaskModel): ClientTaskModel {
        val json = JSONObject().accumulate("action", task.action)
            .accumulate("payload", task.payload)
            .accumulate("time_offset", task.timeOffset)
            .accumulate("continue_on_failure", task.continueOnFailure)
        return ClientScheduleManager(server, baseRequest).parseClientTask(JSONObject(
            baseRequest.executeRequest(
                ClientRoutes.SCHEDULES.createTask(server.attributes.identifier, scheduleId),
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
        baseRequest.executeRequest(ClientRoutes.SCHEDULES.updateSchedule(server.attributes.identifier, scheduleId),json.toString())
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.SCHEDULES.deleteSchedule(server.attributes.identifier, scheduleId), null)
    }

}