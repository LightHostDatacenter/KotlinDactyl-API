package br.com.lighthost.kotlinDactylApi.impl.application.servers.actions

import br.com.lighthost.kotlinDactylApi.impl.application.servers.ApplicationServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject

class ApplicationServerDetailsActions(private val baseRequest: BaseRequest, private val server: ApplicationServerDetails) {

    fun update(){
        val json = JSONObject()
            .accumulate("name", server.details.name)
            .accumulate("user", server.details.owner)
            .accumulate("external_id", server.details.externalId)
            .accumulate("description", server.details.description)
        baseRequest.executeRequest(ApplicationRoutes.SERVERS.updateServerDetails(server.details.id), json.toString())
    }

}