package kotlinDactyl.client.schedules.models

import kotlinDactyl.client.schedules.ClientScheduleManager
import kotlinDactyl.client.schedules.actions.ScheduleActions
import java.time.OffsetDateTime

data class ClientScheduleModel(
    val id:Int,
    val name:String,
    val cron:ClientCronJobModel,
    val isActive:Boolean,
    val isProcessing:Boolean,
    val onlyWhenOnline:Boolean,
    val lastRun:OffsetDateTime?,
    val nextRun:OffsetDateTime,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime,
    val tasks:List<ClientTaskModel>?,
    val actions:ScheduleActions,
    val updateModel: NewScheduleModel)
