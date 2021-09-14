package br.com.lighthost.kotlinDactylApi.client.server.managers.schedules

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.schedules.models.*
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ClientScheduleManager( private val server: ClientServerDetails, private val baseRequest: BaseRequest) {

    fun retrieveSchedules(): List<ClientScheduleModel>? {
        val list:MutableList<ClientScheduleModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.SCHEDULES.getSchedules(server.attributes.identifier), null)).getJSONArray("data").forEach {
            it as JSONObject
            list.add(parseClientSchedule(it.getJSONObject("attributes").toString()))
        }
        return if(list.isEmpty()){
            null
        } else {
            list
        }
    }

    fun retrieveSchedulesById(id:Int): ClientScheduleModel {
        return parseClientSchedule(JSONObject(baseRequest.executeRequest(
            ClientRoutes.SCHEDULES.getSchedule(server.attributes.identifier, id), null))
            .getJSONObject("attributes").toString())
    }

    fun createSchedule(schedule:NewScheduleModel): ClientScheduleModel {
        val json = JSONObject().accumulate("name", schedule.name)
            .accumulate("is_active", schedule.isActive)
            .accumulate("minute", schedule.cron.minute)
            .accumulate("hour", schedule.cron.hour)
            .accumulate("day_of_week", schedule.cron.dayOfWeek)
            .accumulate("month", schedule.cron.month)
            .accumulate("day_of_month", schedule.cron.dayOfMonth)
            .accumulate("only_when_online", schedule.onlyWhenOnline)
        return parseClientSchedule(JSONObject(baseRequest.executeRequest(
            ClientRoutes.SCHEDULES.createSchedule(server.attributes.identifier), json.toString()))
            .getJSONObject("attributes").toString())
    }

        private fun parseClientSchedule(rawJson : String): ClientScheduleModel {
            val json = JSONObject(rawJson)
            val cronJson = json.getJSONObject("cron")
            val cron = ClientCronJobModel(
                cronJson.getString("day_of_week"),
                cronJson.getString("day_of_month"),
                cronJson.getString("month"),
                cronJson.getString("hour"),
                cronJson.getString("minute")
            )
            val tasks: MutableList<ClientTaskModel> = mutableListOf()
            json.getJSONObject("relationships").getJSONObject("tasks").getJSONArray("data").forEach {
                it as JSONObject
                tasks.add(parseClientTask(it.getJSONObject("attributes").toString(),json.getInt("id")))
            }
            return ClientScheduleModel(
                json.getInt("id"),
                json.getString("name"),
                json.getBoolean("is_active"),
                json.getBoolean("only_when_online"),
                cron,
                json.getBoolean("is_processing"),
                (if (json.get("last_run_at").toString() != "null") { OffsetDateTime.parse(json.getString("last_run_at")) } else{null}),
                OffsetDateTime.parse(json.getString("next_run_at")),
                OffsetDateTime.parse(json.getString("created_at")),
                OffsetDateTime.parse(json.getString("updated_at")),
                tasks,
                baseRequest,
                server)
        }

         fun parseClientTask(rawJson : String, scheduleId:Int): ClientTaskModel {
            val json = JSONObject(rawJson)
             return ClientTaskModel(
                json.getInt("id"),
                json.getInt("sequence_id"),
                json.getString("action"),
                json.getString("payload"),
                json.getInt("time_offset"),
                 json.getBoolean("continue_on_failure"),
                 json.getBoolean("is_queued"),
                OffsetDateTime.parse(json.getString("created_at")),
                OffsetDateTime.parse(json.getString("updated_at")),
                scheduleId,
                baseRequest,
                server)
        }

}