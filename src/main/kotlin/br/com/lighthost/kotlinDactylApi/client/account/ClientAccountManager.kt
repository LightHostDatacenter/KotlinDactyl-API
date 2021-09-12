package br.com.lighthost.kotlinDactylApi.client.account

import br.com.lighthost.kotlinDactylApi.client.account.actions.ClientAccountActions
import br.com.lighthost.kotlinDactylApi.client.account.actions.ClientApiKeysActions
import br.com.lighthost.kotlinDactylApi.client.account.models.ClientAccountDetailsModel
import br.com.lighthost.kotlinDactylApi.client.account.models.ClientApiKeyModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ClientAccountManager(private val baseRequest: BaseRequest) {

    val details:ClientAccountDetailsModel =
        accountDetailsParser(JSONObject(baseRequest.executeRequest(ClientRoutes.ACCOUNT.getDetails(), null))
            .getJSONObject("attributes").toString())

    fun retrieveOtpauth():String{
        return JSONObject(baseRequest.executeRequest(ClientRoutes.ACCOUNT.get2FADetails(), null)).getJSONObject("data").getString("image_url_data")
    }

    fun generateApiKey(description:String, allowedIps: List<String>?): String {
        val json = JSONObject().accumulate("description", description).accumulate("allowed_ips", allowedIps)
        return JSONObject(baseRequest.executeRequest(ClientRoutes.ACCOUNT.createApiKey(), json.toString())).getJSONObject("meta").getString("secret_token")
    }

    fun retrieveApiKeys(): MutableList<ClientApiKeyModel> {
        val list:MutableList<ClientApiKeyModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.ACCOUNT.getApiKeys(), null)).getJSONArray("data").forEach {
            it as JSONObject
            list.add(clientApiKeyParser(it.getJSONObject("attributes").toString()))
        }
        return list
    }

    fun retrieveApiKey(identifier:String): ClientApiKeyModel {
        return retrieveApiKeys().first { it.identifier == identifier }
    }

    private fun clientApiKeyParser(rawJson : String): ClientApiKeyModel {
        val json = JSONObject(rawJson)
        return ClientApiKeyModel(
            json.getString("identifier"),
            json.getString("description"),
            json.getJSONArray("allowed_ips").map { it as String },
            (if (json.get("last_used_at").toString() != "null") { OffsetDateTime.parse(json.getString("last_used_at")) } else{null}),
            OffsetDateTime.parse(json.getString("created_at")),
            ClientApiKeysActions(baseRequest,json.getString("identifier")))
    }

    private fun accountDetailsParser(rawJson : String): ClientAccountDetailsModel {
        val json = JSONObject(rawJson)
        return ClientAccountDetailsModel(
            json.getInt("id"),
            json.getBoolean("admin"),
            json.getString("username"),
            json.getString("email"),
            json.getString("first_name"),
            json.getString("last_name"),
            json.getString("language"),
            ClientAccountActions(baseRequest))
        }

}