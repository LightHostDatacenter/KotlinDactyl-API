package br.com.lighthost.kotlinDactylApi.client.backups.actions

import br.com.lighthost.kotlinDactylApi.client.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientBackupActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val uuid:String) {

    fun toggleLock(): Boolean {
        return  JSONObject(
            baseRequest.executeRequest(ClientRoutes.BACKUPS.toggleLock(server.identifier,uuid), "")
        ).getJSONObject("attributes").getBoolean("is_locked")
    }

    fun getDownloadLink(): String {
        return JSONObject(baseRequest.executeRequest(ClientRoutes.BACKUPS.getBackupDownload(server.identifier, uuid), null)).getJSONObject("attributes").getString("url")
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.BACKUPS.deleteBackup(server.identifier, uuid), null)
    }

}