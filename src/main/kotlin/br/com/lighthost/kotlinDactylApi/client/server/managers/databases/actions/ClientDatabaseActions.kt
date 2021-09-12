package br.com.lighthost.kotlinDactylApi.client.server.managers.databases.actions

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ClientDatabaseActions(private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val id:String) {

    fun rotatePassword(): String {
        return JSONObject(baseRequest.executeRequest(ClientRoutes.DATABASES.rotatePassword(server.attributes.identifier, id), null)).getJSONObject("attributes")
            .getJSONObject("relationships").getJSONObject("password").getJSONObject("attributes").getString("password")
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.DATABASES.deleteDatabase(server.attributes.identifier, id), null)
    }

}