package br.com.lighthost.kotlinDactylApi.client

import br.com.lighthost.kotlinDactylApi.client.backups.ClientBackupManager
import br.com.lighthost.kotlinDactylApi.client.console.ConsoleHandler
import br.com.lighthost.kotlinDactylApi.client.console.ConsoleWebSocket
import br.com.lighthost.kotlinDactylApi.client.databases.ClientDatabaseManager
import br.com.lighthost.kotlinDactylApi.client.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.files.ClientFileManager
import br.com.lighthost.kotlinDactylApi.client.network.ClientNetworkManager
import br.com.lighthost.kotlinDactylApi.client.poweractions.ClientPowerManager
import br.com.lighthost.kotlinDactylApi.client.schedules.ClientScheduleManager
import br.com.lighthost.kotlinDactylApi.client.serversettings.ClientServerSettingsManager
import br.com.lighthost.kotlinDactylApi.client.startup.ClientStartupManager
import br.com.lighthost.kotlinDactylApi.client.subusers.ClientSubUsersManager
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import org.json.JSONObject

class ClientServer (jsonResponse:String, private val baseRequest:BaseRequest) {

    private val server = ClientServerDetails(JSONObject(jsonResponse).getJSONObject("attributes"))

    val details: ClientServerDetails = server
    val fileManager = ClientFileManager(server, baseRequest)
    val databaseManager = ClientDatabaseManager(server, baseRequest)
    val networkManager = ClientNetworkManager(server, baseRequest)
    val backupManager = ClientBackupManager(server, baseRequest)
    val powerManager = ClientPowerManager(server, baseRequest)
    val startupManager = ClientStartupManager(server, baseRequest)
    val settings = ClientServerSettingsManager(server, baseRequest)
    val scheduleManager = ClientScheduleManager(server, baseRequest)
    val subUserManager = ClientSubUsersManager(server, baseRequest)

    fun getConsoleInstance(handlerClass:ConsoleHandler): ConsoleWebSocket {
        return ConsoleWebSocket(this, baseRequest, handlerClass)
    }

}