package br.com.lighthost.kotlinDactylApi.impl.application.servers.actions

import br.com.lighthost.kotlinDactylApi.impl.application.servers.ApplicationServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject

class ApplicationServerBuildActions (private val baseRequest: BaseRequest, private val server: ApplicationServerDetails) {

    fun update(){
        val featureLimits = JSONObject().accumulate("databases", server.build.featureLimits.databases).accumulate("backups", server.build.featureLimits.backups).accumulate("allocations", server.build.featureLimits.allocations)
        val json = JSONObject()
            .accumulate("allocation", server.build.primaryAllocation)
            .accumulate("memory", server.build.limits.memory)
            .accumulate("swap", server.build.limits.swap)
            .accumulate("io", server.build.limits.io)
            .accumulate("cpu", server.build.limits.cpu)
            .accumulate("disk", server.build.limits.disk)
            .accumulate("feature_limits", featureLimits)
        baseRequest.executeRequest(ApplicationRoutes.SERVERS.updateServerBuild(server.details.id), json.toString())
    }

}