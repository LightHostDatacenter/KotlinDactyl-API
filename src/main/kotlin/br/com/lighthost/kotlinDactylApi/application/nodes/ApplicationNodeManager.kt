package br.com.lighthost.kotlinDactylApi.application.nodes

import br.com.lighthost.kotlinDactylApi.application.nodes.models.ApplicationNewNodeModel
import br.com.lighthost.kotlinDactylApi.application.nodes.models.ApplicationNodeModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ApplicationNodeManager(private val baseRequest: BaseRequest) {

    fun retrieveNodes(): MutableList<ApplicationNodeModel> {
        val list:MutableList<ApplicationNodeModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ApplicationRoutes.NODES.getNodes(), null)).getJSONArray("data").forEach {
            it as JSONObject
            list.add(parseNodeModel(it.getJSONObject("attributes")))
        }
        return list
    }

    fun retrieveNodeById(id:Int): ApplicationNodeModel {
        return parseNodeModel(JSONObject(baseRequest.executeRequest
            (ApplicationRoutes.NODES.getNode(id), null)).getJSONObject("attributes"))
    }

    fun createNode(newNode: ApplicationNewNodeModel): ApplicationNodeModel {
        val json = JSONObject()
            .accumulate("name", newNode.name)
            .accumulate("description", newNode.description)
            .accumulate("location_id", newNode.locationId)
            .accumulate("fqdn", newNode.fqdn)
            .accumulate("scheme", newNode.scheme)
            .accumulate("memory", newNode.memory)
            .accumulate("memory_overallocate", newNode.memoryOverAllocate)
            .accumulate("disk", newNode.disk)
            .accumulate("disk_overallocate", newNode.diskOverAllocate)
            .accumulate("upload_size", newNode.uploadSize)
            .accumulate("daemon_sftp", newNode.daemonSftpPort)
            .accumulate("daemon_listen", newNode.daemonListenPort)
            .accumulate("public", newNode.isPublic)
            .accumulate("behind_proxy", newNode.isBehindProxy)
        return parseNodeModel(JSONObject(baseRequest.executeRequest
            (ApplicationRoutes.NODES.createNode(), json.toString())).getJSONObject("attributes"))
    }

    private fun parseNodeModel(json: JSONObject): ApplicationNodeModel {
        val serversList: MutableList<Int> = mutableListOf()
        json.optJSONObject("relationships")?.optJSONObject("servers")?.optJSONArray("data")?.forEach {
            it as JSONObject
            serversList.add(it.getJSONObject("attributes").getInt("id"))
        }
        return ApplicationNodeModel(
            json.getInt("id"),
            json.getString("uuid"),
            json.getBoolean("public"),
            json.getString("name"),
            json.optString("description"),
            json.getInt("location_id"),
            json.getString("fqdn"),
            json.getString("scheme"),
            json.getBoolean("behind_proxy"),
            json.getBoolean("maintenance_mode"),
            json.getLong("memory"),
            json.getLong("memory_overallocate"),
            json.getLong("disk"),
            json.getLong("disk_overallocate"),
            json.getLong("upload_size"),
            json.getLong("daemon_listen"),
            json.getLong("daemon_sftp"),
            json.getString("daemon_base"),
            OffsetDateTime.parse(json.getString("created_at")),
            OffsetDateTime.parse(json.getString("updated_at")),
            json.getJSONObject("allocated_resources").getLong("memory"),
            json.getJSONObject("allocated_resources").getLong("disk"),
            serversList,
            baseRequest)
    }

}