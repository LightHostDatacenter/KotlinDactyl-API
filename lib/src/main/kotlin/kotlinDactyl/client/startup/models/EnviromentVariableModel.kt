package kotlinDactyl.client.startup.models

data class EnviromentVariableModel(
    val name:String,
    val description:String,
    val envVariable:String,
    val defaultValue:String,
    val serverValue:String,
    val isEditable:Boolean,
    val rules:String)
