package br.com.lighthost.kotlinDactylApi.client.account

import br.com.lighthost.kotlinDactylApi.client.account.models.ClientAccountDetailsModel
import br.com.lighthost.kotlinDactylApi.client.account.models.ClientApiKeyModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ClientAccountManager(private val baseRequest: BaseRequest) {

    val details:ClientAccountDetailsModel =
         AccountDetailsParser.parse(JSONObject(baseRequest.executeRequest(ClientRoutes.ACCOUNT.getDetails(), null))
            .getJSONObject("attributes").toString())

    fun get2FAOtpauth():String{
        return JSONObject(baseRequest.executeRequest(ClientRoutes.ACCOUNT.get2FADetails(), null)).getJSONObject("data").getString("image_url_data")
    }

    fun enable2FA(code:String): List<String> {
        val json = JSONObject().accumulate("code", code)
        return JSONObject(baseRequest.executeRequest(ClientRoutes.ACCOUNT.enable2FA(), json.toString()))
            .getJSONObject("attributes").getJSONArray("tokens").toList().map { it as String }
    }

    fun disable2FA(password:String){
        val json = JSONObject().accumulate("password", password)
        baseRequest.executeRequest(ClientRoutes.ACCOUNT.disable2FA(), json.toString())
    }

    fun updateEmail(newEmail:String, password: String){
        val json = JSONObject().accumulate("email", newEmail).accumulate("password", password)
        baseRequest.executeRequest(ClientRoutes.ACCOUNT.updateEmail(), json.toString())
    }

    fun updatePassword(password: String, newPassword:String){
        val json = JSONObject().accumulate("current_password", password).accumulate("password", newPassword).accumulate("password_confirmation", newPassword)
        baseRequest.executeRequest(ClientRoutes.ACCOUNT.updatePassword(), json.toString())
    }

    fun generateApiKey(description:String, allowedIps: List<String>?): String {
        val json = JSONObject().accumulate("description", description).accumulate("allowed_ips", allowedIps)
        return JSONObject(baseRequest.executeRequest(ClientRoutes.ACCOUNT.createApiKey(), json.toString())).getJSONObject("meta").getString("secret_token")
    }

    fun getApiKeysList(): MutableList<ClientApiKeyModel> {
        val list:MutableList<ClientApiKeyModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.ACCOUNT.getApiKeys(), null)).getJSONArray("data").forEach {
            it as JSONObject
            list.add(ClientApiKeyParser.parse(it.getJSONObject("attributes").toString()))
        }
        return list
    }

    fun getApiKey(identifier:String):ClientApiKeyModel{
        return getApiKeysList().first { it.identifier == identifier }
    }

    fun deleteApiKey(identifier: String){
        baseRequest.executeRequest(ClientRoutes.ACCOUNT.deleteApiKey(identifier), null)
    }

    object AccountDetailsParser {
        fun parse(rawJson : String): ClientAccountDetailsModel {
            val json = JSONObject(rawJson)
            return ClientAccountDetailsModel(
                json.getInt("id"),
                json.getBoolean("admin"),
                json.getString("username"),
                json.getString("email"),
                json.getString("first_name"),
                json.getString("last_name"),
                json.getString("language"))
        }
    }

    object ClientApiKeyParser {
        fun parse(rawJson : String): ClientApiKeyModel {
            val json = JSONObject(rawJson)
            return ClientApiKeyModel(
                json.getString("identifier"),
                json.getString("description"),
                json.getJSONArray("allowed_ips").map { it as String },
                (if (json.get("last_used_at").toString() != "null") { OffsetDateTime.parse(json.getString("last_used_at")) } else{null}),
                OffsetDateTime.parse(json.getString("created_at")))
        }
    }

}