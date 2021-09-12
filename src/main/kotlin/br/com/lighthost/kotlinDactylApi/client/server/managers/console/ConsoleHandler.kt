package br.com.lighthost.kotlinDactylApi.client.server.managers.console

import br.com.lighthost.kotlinDactylApi.client.server.managers.console.models.ConsoleBackupModel
import br.com.lighthost.kotlinDactylApi.client.server.managers.console.models.ConsoleStatsModel

interface ConsoleHandler {
    fun onConsoleOutput(message:String)
    fun onDaemonMessage(message:String)
    fun onDaemonError(message:String)
    fun onInstallStarted()
    fun onInstallOutput(message:String)
    fun onInstallCompleted()
    fun onStats(stats: ConsoleStatsModel)
    fun onStatus(status:String)
    fun onBackupCompleted(backup:ConsoleBackupModel)
    fun onAuthSuccess(commander: ConsoleCommander)
    fun onFailure(t:Throwable)
    fun onTransferSuccess()
    fun onTransferMessage(message: String)
}