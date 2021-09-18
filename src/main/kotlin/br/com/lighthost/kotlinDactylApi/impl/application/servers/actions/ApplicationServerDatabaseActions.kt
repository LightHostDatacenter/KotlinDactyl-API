package br.com.lighthost.kotlinDactylApi.impl.application.servers.actions

import br.com.lighthost.kotlinDactylApi.impl.application.servers.ApplicationServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject

class ApplicationServerDatabaseActions (private val baseRequest: BaseRequest, private val server:ApplicationServerDetails, private val databaseId:Int) {

    fun retrievePassword(): String {
        return JSONObject(baseRequest.executeRequest(ApplicationRoutes.DATABASES.getDatabase(server.details.id, databaseId), null))
            .getJSONObject("attributes").getJSONObject("relationships")
            .getJSONObject("password").getJSONObject("attributes")
            .getString("password")
    }

    fun delete(){
        baseRequest.executeRequest(ApplicationRoutes.DATABASES.deleteDatabase(server.details.id, databaseId), null)
    }

    fun rotatePassword(){
        baseRequest.executeRequest(ApplicationRoutes.DATABASES.rotateDatabasePassword(server.details.id, databaseId), null)
    }

}