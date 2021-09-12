package br.com.lighthost.kotlinDactylApi.client.account.actions

import br.com.lighthost.kotlinDactylApi.client.account.models.ClientApiKeyModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.RouteModels.ClientRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ClientAccountActions(private val baseRequest: BaseRequest) {

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

}

