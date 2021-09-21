package br.com.lighthost.kotlinDactylApi.impl.client.server

import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.backups.ClientBackupManager
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.console.ConsoleHandler
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.console.ConsoleWebSocket
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.databases.ClientDatabaseManager
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.files.ClientFileManager
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.network.ClientNetworkManager
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.schedules.ClientScheduleManager
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.startup.ClientStartupManager
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.subusers.ClientSubUsersManager
import br.com.lighthost.kotlinDactylApi.impl.client.server.utils.commands.ClientServerCommandSender
import br.com.lighthost.kotlinDactylApi.impl.client.server.utils.power.ClientPowerManager
import br.com.lighthost.kotlinDactylApi.impl.client.server.utils.resourceusage.ClientServerResourceUsageManager
import br.com.lighthost.kotlinDactylApi.impl.client.server.utils.serversettings.ClientServerSettingsManager
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import org.json.JSONObject

class ClientServer (jsonResponse:String, private val baseRequest:BaseRequest) {

    private val server = ClientServerDetails(JSONObject(jsonResponse))
    private val clientServer = this

        val details: ClientServerDetails = server
        val fileManager = ClientFileManager(server, baseRequest)
        val databaseManager = ClientDatabaseManager(server, baseRequest)
        val networkManager = ClientNetworkManager(server, baseRequest)
        val backupManager = ClientBackupManager(server, baseRequest)
        val startupManager = ClientStartupManager(server, baseRequest)
        val scheduleManager = ClientScheduleManager(server, baseRequest)
        val subUserManager = ClientSubUsersManager(server, baseRequest)
        val settings = ClientServerSettingsManager(server, baseRequest)
        val powerManager = ClientPowerManager(server, baseRequest)
        val resourceUsage = ClientServerResourceUsageManager(baseRequest, server)
        val commandSender = ClientServerCommandSender(baseRequest, server)

    fun createConsoleInstance(handlerClass:ConsoleHandler): ConsoleWebSocket {
        return ConsoleWebSocket(clientServer, baseRequest, handlerClass)
    }

}