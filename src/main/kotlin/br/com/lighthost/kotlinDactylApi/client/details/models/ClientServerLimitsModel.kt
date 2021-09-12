package br.com.lighthost.kotlinDactylApi.client.details.models

data class ClientServerLimitsModel(
    val memory:Int,
    val swap:Int,
    val disk:Int,
    val io:Int,
    val cpu:Int)
