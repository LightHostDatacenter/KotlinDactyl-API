package br.com.lighthost.kotlinDactylApi.impl.application.servers

import br.com.lighthost.kotlinDactylApi.impl.application.servers.models.ApplicationNewServerModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject

class ApplicationServerManager(private val baseRequest: BaseRequest) {

    fun retrieveServers(): MutableList<ApplicationServerDetails> {
        val list: MutableList<ApplicationServerDetails> = mutableListOf()
        val serversArray =
            JSONObject(baseRequest.executeRequest(ApplicationRoutes.SERVERS.getServers(), null)).getJSONArray("data")
        for (i: Int in 0 until serversArray.length()) {
            list.add(
                ApplicationServerDetails(
                    JSONObject(serversArray[i].toString()).getJSONObject("attributes"),
                    baseRequest
                )
            )
        }
        return list
    }

    fun retrieveServerById(id:Int): ApplicationServerDetails {
        return ApplicationServerDetails(JSONObject(baseRequest.executeRequest(ApplicationRoutes.SERVERS.getServer(id), null)).getJSONObject("attributes"), baseRequest)
    }

    fun retrieveServerByExternalId(externalId:String): ApplicationServerDetails {
        return ApplicationServerDetails(JSONObject(baseRequest.executeRequest(ApplicationRoutes.SERVERS.getServerByExternalId(externalId), null)).getJSONObject("attributes"), baseRequest)
    }

    fun createServer(newServer:ApplicationNewServerModel): ApplicationServerDetails {
        val featureLimits = JSONObject().accumulate("databases", newServer.featureLimits.databases).accumulate("backups", newServer.featureLimits.backups).accumulate("allocations", newServer.featureLimits.allocations)
        val limits = JSONObject().accumulate("memory", newServer.limits.memory).accumulate("disk", newServer.limits.disk).accumulate("cpu", newServer.limits.cpu).accumulate("io", newServer.limits.io).accumulate("swap", newServer.limits.swap)
        val allocation = JSONObject().accumulate("default", newServer.primaryAllocation)
        val json = JSONObject()
            .accumulate("name", newServer.name)
            .accumulate("description", newServer.description)
            .accumulate("external_id", newServer.externalId)
            .accumulate("user", newServer.user)
            .accumulate("egg", newServer.egg)
            .accumulate("docker_image", newServer.docker_image)
            .accumulate("startup", newServer.startup)
            .accumulate("environment", newServer.envs)
            .accumulate("feature_limits", featureLimits)
            .accumulate("limits", limits)
            .accumulate("allocation", allocation)
        return ApplicationServerDetails(JSONObject(baseRequest.executeRequest(ApplicationRoutes.SERVERS.createServer(), json.toString())).getJSONObject("attributes"), baseRequest)
    }

}