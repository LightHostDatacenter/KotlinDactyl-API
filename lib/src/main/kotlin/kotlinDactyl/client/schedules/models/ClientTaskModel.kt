package kotlinDactyl.client.schedules.models

import kotlinDactyl.client.schedules.ClientScheduleManager
import kotlinDactyl.client.schedules.actions.TaskActions
import java.time.OffsetDateTime

data class ClientTaskModel(
    val id:Int,
    val sequenceId:Int,
    val action:String,
    val payload:String,
    val timeOffset:Int,
    val isQueued:Boolean,
    val continueOnFailure:Boolean,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime?,
    val actions: TaskActions,
    var updateModel:NewTaskModel
)
