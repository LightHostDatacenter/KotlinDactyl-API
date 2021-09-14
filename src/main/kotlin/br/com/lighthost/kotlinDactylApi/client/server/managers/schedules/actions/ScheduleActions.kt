package br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.actions

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.ClientScheduleManager
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.models.ClientScheduleModel
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.models.ClientTaskModel
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.models.NewTaskModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ScheduleActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val schedule:ClientScheduleModel){

    fun createTask(task:NewTaskModel): ClientTaskModel {
        val json = JSONObject().accumulate("action", task.action)
            .accumulate("payload", task.payload)
            .accumulate("time_offset", task.timeOffset)
            .accumulate("continue_on_failure", task.continueOnFailure)
        return ClientScheduleManager(server, baseRequest).parseClientTask(JSONObject(
            baseRequest.executeRequest(
                ClientRoutes.SCHEDULES.createTask(server.attributes.identifier, schedule.id),
                json.toString())).getJSONObject("attributes").toString(), schedule.id)
    }

    fun update(){
        val json = JSONObject().accumulate("name", schedule.name)
            .accumulate("is_active", schedule.isActive)
            .accumulate("minute", schedule.cron.minute)
            .accumulate("hour", schedule.cron.hour)
            .accumulate("day_of_week", schedule.cron.dayOfWeek)
            .accumulate("month", schedule.cron.month)
            .accumulate("day_of_month", schedule.cron.dayOfMonth)
            .accumulate("only_when_online", schedule.onlyWhenOnline)
        baseRequest.executeRequest(ClientRoutes.SCHEDULES.updateSchedule(server.attributes.identifier, schedule.id),json.toString())
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.SCHEDULES.deleteSchedule(server.attributes.identifier, schedule.id), null)
    }

}