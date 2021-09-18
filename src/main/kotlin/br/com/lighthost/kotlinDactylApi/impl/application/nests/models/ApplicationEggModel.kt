package br.com.lighthost.kotlinDactylApi.impl.application.nests.models

import org.json.JSONObject
import java.time.OffsetDateTime

data class ApplicationEggModel(
    val id:Int,
    val uuid:String,
    val author:String,
    val name:String,
    val description:String,
    val nest:Int,
    val dockerImage:String,
    val dockerImages:List<String>,
    val config:JSONObject,
    val startup:String,
    val script:JSONObject,
    val variables:List<ApplicationEggVariableModel>,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime)
