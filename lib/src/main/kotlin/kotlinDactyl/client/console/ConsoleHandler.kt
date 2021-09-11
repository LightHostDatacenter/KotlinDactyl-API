package kotlinDactyl.client.console

import kotlinDactyl.client.console.models.ConsoleBackupModel
import kotlinDactyl.client.console.models.ConsoleCommander
import kotlinDactyl.client.console.models.ConsoleStatsModel

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