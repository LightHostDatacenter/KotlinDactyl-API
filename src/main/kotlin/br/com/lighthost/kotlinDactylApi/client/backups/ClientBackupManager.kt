package br.com.lighthost.kotlinDactylApi.client.backups

import br.com.lighthost.kotlinDactylApi.client.backups.actions.ClientBackupActions
import br.com.lighthost.kotlinDactylApi.client.backups.models.ClientBackupModel
import br.com.lighthost.kotlinDactylApi.client.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ClientBackupManager(private val server : ClientServerDetails, private val baseRequest: BaseRequest) {

    fun retrieveBackups():List<ClientBackupModel>{
        val list:MutableList<ClientBackupModel> = mutableListOf()
         JSONObject(baseRequest.executeRequest(ClientRoutes.BACKUPS.listBackups(server.attributes.identifier), null)).getJSONArray("data").forEach {
             it as JSONObject
            list.add(clientBackupParser(it.getJSONObject("attributes").toString(), server, baseRequest))
        }
        return list
    }

    fun retrieveBackup(uuid:String):ClientBackupModel{
        return clientBackupParser( JSONObject(
            baseRequest.executeRequest(ClientRoutes.BACKUPS.getBackup(server.attributes.identifier,uuid), null)
        ).getJSONObject("attributes").toString(), server, baseRequest)
    }

    fun createBackup(name:String, ignoredFiles:List<String>?, isLocked:Boolean): ClientBackupModel {
        var json = JSONObject().accumulate("name", name).accumulate("is_locked", isLocked)
        if(!ignoredFiles.isNullOrEmpty()){
            var ignoredListString = ""
            ignoredFiles.forEach { ignoredListString += it +"\n" }
            json = json.accumulate("ignored", ignoredListString)
        }
        return clientBackupParser( JSONObject(
            baseRequest.executeRequest(ClientRoutes.BACKUPS.createBackup(server.attributes.identifier), json.toString())
        ).getJSONObject("attributes").toString(), server, baseRequest)
    }

    private fun clientBackupParser(rawJson : String, server:ClientServerDetails, baseRequest:BaseRequest): ClientBackupModel {
        val json = JSONObject(rawJson)
        return ClientBackupModel(
            json.getString("uuid"),
            json.getBoolean("is_successful"),
            json.getBoolean("is_locked"),
            json.getString("name"),
            json.getJSONArray("ignored_files").toMutableList().map { it as String },
            json.optString("checksum", null),
            json.getLong("bytes"),
            OffsetDateTime.parse(json.getString("created_at")),
            (if (json.get("completed_at").toString() != "null") { OffsetDateTime.parse(json.getString("completed_at")) } else{null}),
            ClientBackupActions(server, baseRequest,json.getString("uuid"))
        )
    }

}