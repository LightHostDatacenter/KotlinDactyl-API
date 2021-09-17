package br.com.lighthost.kotlinDactylApi.application.nests.models

import java.time.OffsetDateTime

data class ApplicationEggVariableModel (
    val name:String,
    val description:String,
    val envVariable:String,
    val defaultValue:String,
    val isUserViewable:Boolean,
    val isUserEditable:Boolean,
    val rules:String,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime)
