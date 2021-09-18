package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.schedules.models

data class NewTaskModel (
    var sequenceId: Int,
    var action: String,
    var payload: String?,
    var timeOffset:Int,
    var continueOnFailure:Boolean)