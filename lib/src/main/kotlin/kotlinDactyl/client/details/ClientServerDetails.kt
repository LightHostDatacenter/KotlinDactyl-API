package kotlinDactyl.client.details

import kotlinDactyl.client.details.models.ClientFeatureLimitsModel
import kotlinDactyl.client.details.models.ClientLimitsModel
import kotlinDactyl.client.details.models.ClientSftpDetailsModel
import org.json.JSONObject

class ClientServerDetails(rawJson : JSONObject){

    val isSuspended = rawJson.getBoolean("is_suspended")
    val isInstalling = rawJson.getBoolean("is_installing")

    val sftpDetails = ClientSftpDetailsModel(
        rawJson.getJSONObject("sftp_details").getString("ip"),
        rawJson.getJSONObject("sftp_details").getInt("port"))

    val isOwner:Boolean = rawJson.getBoolean("server_owner")
    val identifier:String = rawJson.getString("identifier")
    val uuid:String = rawJson.getString("identifier")
    val name:String = rawJson.getString("uuid")
    val node:String = rawJson.getString("name")

    val limits = ClientLimitsModel(
        rawJson.getJSONObject("limits").getInt("memory"),
        rawJson.getJSONObject("limits").getInt("swap"),
        rawJson.getJSONObject("limits").getInt("disk"),
        rawJson.getJSONObject("limits").getInt("io"),
        rawJson.getJSONObject("limits").getInt("cpu")
    )

    val featureLimits = ClientFeatureLimitsModel(
        rawJson.getJSONObject("feature_limits").getInt("databases"),
        rawJson.getJSONObject("feature_limits").getInt("allocations"),
        rawJson.getJSONObject("feature_limits").getInt("backups")
    )
}