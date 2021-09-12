package br.com.lighthost.kotlinDactylApi.client.server.managers.startup.models

import br.com.lighthost.kotlinDactylApi.client.server.managers.startup.actions.ClientStartupActions

data class EnvironmentVariableModel(
    val name:String,
    val description:String,
    val envVariable:String,
    val defaultValue:String,
    val serverValue:String,
    val isEditable:Boolean,
    val rules:String,
    val actions:ClientStartupActions)
