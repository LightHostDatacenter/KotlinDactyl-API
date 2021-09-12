package br.com.lighthost.kotlinDactylApi.client.server.utils.resourceusage

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.utils.resourceusage.models.ClientServerResourceUsageModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ClientServerResourceUsageManager(private val baseRequest: BaseRequest, private val server:ClientServerDetails) {

    fun retrieveResourceUsage(): ClientServerResourceUsageModel {
        val data = JSONObject(baseRequest.executeRequest(ClientRoutes.SERVER.getResourcesUsage(server.attributes.identifier), null)).getJSONObject("attributes")
        return ClientServerResourceUsageModel(
            data.getBoolean("is_suspended"),
            data.getJSONObject("resources").getLong("memory_bytes"),
            data.getJSONObject("resources").getDouble("cpu_absolute"),
            data.getJSONObject("resources").getLong("network_rx_bytes"),
            data.getJSONObject("resources").getLong("network_tx_bytes"),
            data.getString("current_state"),
            data.getJSONObject("resources").getLong("disk_bytes"))
    }

}