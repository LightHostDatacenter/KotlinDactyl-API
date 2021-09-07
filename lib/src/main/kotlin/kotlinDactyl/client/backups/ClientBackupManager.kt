package kotlinDactyl.client.backups

import kotlinDactyl.client.backups.models.ClientBackupModel
import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.requests.BaseRequest
import kotlinDactyl.requests.RouteModels.ClientRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ClientBackupManager(private val server : ClientServerDetails, private val baseRequest: BaseRequest) {

    fun getBackups():List<ClientBackupModel>{
        val list:MutableList<ClientBackupModel> = mutableListOf()
         JSONObject(baseRequest.executeRequest(ClientRoutes.BACKUPS.listBackups(server.identifier), null)).getJSONArray("data").forEach {
             it as JSONObject
            list.add(ClientBackupParser.parse(it.getJSONObject("attributes").toString()))
        }
        return list
    }

    fun getBackup(uuid:String):ClientBackupModel{
        return ClientBackupParser.parse( JSONObject(
            baseRequest.executeRequest(ClientRoutes.BACKUPS.getBackup(server.identifier,uuid), null)
        ).getJSONObject("attributes").toString())
    }

    fun createBackup(name:String, ignoredFiles:String?, isLocked:Boolean): ClientBackupModel {
        val json = JSONObject().accumulate("name", name).accumulate("ignored", ignoredFiles).accumulate("is_locked", isLocked)
        return ClientBackupParser.parse( JSONObject(
            baseRequest.executeRequest(ClientRoutes.BACKUPS.createBackup(server.identifier), json.toString())
        ).getJSONObject("attributes").toString())
    }

    fun getDownloadLink(uuid:String): String {
        return JSONObject(baseRequest.executeRequest(ClientRoutes.BACKUPS.getBackupDownload(server.identifier, uuid), null)).getJSONObject("attributes").getString("url")
    }

    fun deleteBackup(uuid:String){
        baseRequest.executeRequest(ClientRoutes.BACKUPS.deleteBackup(server.identifier, uuid), null)
    }

    object ClientBackupParser {
        fun parse(rawJson : String): ClientBackupModel {
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
                (if (json.get("completed_at").toString() != "null") { OffsetDateTime.parse(json.getString("completed_at")) } else{null}) )
        }
    }

}