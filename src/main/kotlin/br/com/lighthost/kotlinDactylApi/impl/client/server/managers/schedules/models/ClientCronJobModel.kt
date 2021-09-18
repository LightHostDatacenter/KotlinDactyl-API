package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.schedules.models

data class ClientCronJobModel(
    var dayOfWeek:String,
    var dayOfMonth:String,
    var month:String,
    var hour:String,
    var minute:String
)
