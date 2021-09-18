package br.com.lighthost.kotlinDactylApi.impl.application.servers.actions

import br.com.lighthost.kotlinDactylApi.impl.application.servers.ApplicationServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject

class ApplicationServerStartupActions (private val baseRequest: BaseRequest, private val server:ApplicationServerDetails) {

    fun update(){
        val json = JSONObject()
            .accumulate("startup", server.startup.startup)
            .accumulate("environment", server.startup.environment)
            .accumulate("egg", server.startup.egg)
            .accumulate("image", server.startup.image)
            .accumulate("skip_scripts", server.startup.skipScripts)
        baseRequest.executeRequest(ApplicationRoutes.SERVERS.updateServerStartup(server.details.id), json.toString())
    }

}