package br.com.lighthost.kotlinDactylApi.client.schedules.models

data class ClientCronJobModel(
    var dayOfWeek:String,
    var dayOfMonth:String,
    var month:String,
    var hour:String,
    var minute:String
)
