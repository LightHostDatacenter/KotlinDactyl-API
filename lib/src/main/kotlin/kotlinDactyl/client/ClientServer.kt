package kotlinDactyl.client

import kotlinDactyl.client.backups.ClientBackupManager
import kotlinDactyl.client.databases.ClientDatabaseManager
import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.client.files.ClientFileManager
import kotlinDactyl.client.network.ClientNetworkManager
import kotlinDactyl.client.poweractions.ClientPowerManager
import kotlinDactyl.client.schedules.ClientScheduleManager
import kotlinDactyl.client.serversettings.ClientServerSettingsManager
import kotlinDactyl.client.startup.ClientStartupManager
import kotlinDactyl.client.subusers.ClientSubUsersManager
import kotlinDactyl.requests.BaseRequest
import org.json.JSONObject

class ClientServer (jsonResponse:String, baseRequest:BaseRequest) {

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

}