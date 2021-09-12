package br.com.lighthost.kotlinDactylApi.client.schedules.models

import br.com.lighthost.kotlinDactylApi.client.schedules.actions.TaskActions
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
