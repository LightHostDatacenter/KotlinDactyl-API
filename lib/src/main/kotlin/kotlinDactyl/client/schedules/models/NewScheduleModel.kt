package kotlinDactyl.client.schedules.models

data class NewScheduleModel(
    var name:String,
    var isActive:Boolean?,
    val cron:ClientCronJobModel,
    var onlyWhenOnline:Boolean)
