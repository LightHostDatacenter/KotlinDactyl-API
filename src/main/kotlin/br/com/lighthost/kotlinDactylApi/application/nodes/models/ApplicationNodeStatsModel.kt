package br.com.lighthost.kotlinDactylApi.application.nodes.models

data class ApplicationNodeStatsModel(
    val version:String,
    val kernelVersion:String,
    val architecture:String,
    val os:String,
    val cpuCount:Int)
