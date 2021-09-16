package br.com.lighthost.kotlinDactylApi.application.nodes.actions

import br.com.lighthost.kotlinDactylApi.application.nodes.models.ApplicationAllocationModel
import br.com.lighthost.kotlinDactylApi.application.nodes.models.ApplicationNodeModel
import br.com.lighthost.kotlinDactylApi.application.nodes.models.ApplicationNodeStatsModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import br.com.lighthost.kotlinDactylApi.requests.routes.models.RouteModel
import org.json.JSONObject

class ApplicationNodeActions(private val baseRequest: BaseRequest, private val node:ApplicationNodeModel) {

    fun retrieveNodeConfiguration(): String {
        return baseRequest.executeRequest(ApplicationRoutes.NODES.getNodeConfiguration(node.id), null)
    }

    fun update(){
        val json = JSONObject()
            .accumulate("name", node.name)
            .accumulate("location_id", node.locationId)
            .accumulate("fqdn", node.fqdn)
            .accumulate("scheme", node.scheme)
            .accumulate("memory", node.memory)
            .accumulate("memory_overallocate", node.memoryOverAllocate)
            .accumulate("disk", node.disk)
            .accumulate("disk_overallocate", node.diskOverAllocate)
            .accumulate("daemon_sftp", node.daemonSftpPort)
            .accumulate("daemon_listen", node.daemonListenPort)
            .accumulate("description", node.description)
            .accumulate("public", node.isPublic)
            .accumulate("behind_proxy", node.isBehindProxy)
            .accumulate("maintenance_mode", node.isMaintenanceMode)
        baseRequest.executeRequest(ApplicationRoutes.NODES.updateNode(node.id), json.toString())
    }

    fun delete(){
        baseRequest.executeRequest(ApplicationRoutes.NODES.deleteNode(node.id), null)
    }

    fun createAllocations(ip:String, ipAlias:String, ports:List<String>){
        val json = JSONObject().accumulate("ip", ip).accumulate("alias", ipAlias).accumulate("ports", ports)
        baseRequest.executeRequest(ApplicationRoutes.ALLOCATIONS.createAllocation(node.id), json.toString())
    }

    fun retrieveAllocations(): MutableList<ApplicationAllocationModel> {
        val list:MutableList<ApplicationAllocationModel> = mutableListOf()
        val allocationsArray = JSONObject(baseRequest.executeRequest(ApplicationRoutes.ALLOCATIONS.getAllocations(node.id), null)).getJSONArray("data")
        for (i:Int in 0 until allocationsArray.length()){
            val obj = allocationsArray.getJSONObject(i)
            list.add(parseAllocation(obj.getJSONObject("attributes")))
        }
        return list
    }

    fun deleteAllocationsByRange(initialPort:Long, finalPort:Long){
        retrieveAllocations().filter { it.port >= initialPort}.filter { it.port <= finalPort }.forEach {
            it.actions().delete()
        }
    }

    fun retrieveNodeStats(): ApplicationNodeStatsModel {
        val daemonKey = JSONObject(retrieveNodeConfiguration()).getString("token")
        val nodeRequest = BaseRequest(node.scheme + "://" +  node.fqdn, daemonKey)
        return parseNodeStats(JSONObject(nodeRequest.executeRequest(RouteModel("GET", "/api/system", "application/json"), null)))
    }

    private fun parseNodeStats(json: JSONObject): ApplicationNodeStatsModel {
        return ApplicationNodeStatsModel(
            json.getString("version"),
            json.getString("kernel_version"),
            json.getString("architecture"),
            json.getString("os"),
            json.getInt("cpu_count"))
    }

    private fun parseAllocation(json: JSONObject): ApplicationAllocationModel {
        return ApplicationAllocationModel(
            json.getInt("id"),
            json.getString("ip"),
            json.getString("alias"),
            json.getLong("port"),
            json.optString("notes"),
            json.getBoolean("assigned"),
            baseRequest,
            node.id)
    }

}