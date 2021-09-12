package br.com.lighthost.kotlinDactylApi.client.server.managers.details

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.models.ClientServerAttributesModel
import br.com.lighthost.kotlinDactylApi.client.server.managers.details.models.ClientServerFeatureLimitsModel
import br.com.lighthost.kotlinDactylApi.client.server.managers.details.models.ClientServerLimitsModel
import br.com.lighthost.kotlinDactylApi.client.server.managers.details.models.ClientServerSftpDetailsModel
import org.json.JSONObject

class ClientServerDetails(rootJson : JSONObject){

    private val metaObject = rootJson.optJSONObject("meta")
    private val attributesObject: JSONObject = rootJson.getJSONObject("attributes")

    val attributes = ClientServerAttributesModel(
        attributesObject.getBoolean("is_suspended"),
        attributesObject.getBoolean("is_installing"),
        attributesObject.getBoolean("server_owner"),
        attributesObject.getString("identifier"),
        attributesObject.getString("uuid"),
        attributesObject.getString("name"),
        attributesObject.getString("node"),
        attributesObject.getString("description"),
        attributesObject.getInt("internal_id"),
        metaObject?.getJSONArray("user_permissions")?.toList()?.map { it as String })

    val sftpDetails = ClientServerSftpDetailsModel(
        attributesObject.getJSONObject("sftp_details").getString("ip"),
        attributesObject.getJSONObject("sftp_details").getInt("port"))

    val limits = ClientServerLimitsModel(
        attributesObject.getJSONObject("limits").getInt("memory"),
        attributesObject.getJSONObject("limits").getInt("swap"),
        attributesObject.getJSONObject("limits").getInt("disk"),
        attributesObject.getJSONObject("limits").getInt("io"),
        attributesObject.getJSONObject("limits").getInt("cpu"))

    val featureLimits = ClientServerFeatureLimitsModel(
        attributesObject.getJSONObject("feature_limits").getInt("databases"),
        attributesObject.getJSONObject("feature_limits").getInt("allocations"),
        attributesObject.getJSONObject("feature_limits").getInt("backups"))

}