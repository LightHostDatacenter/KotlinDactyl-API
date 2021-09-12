package br.com.lighthost.kotlinDactylApi.api.client

import br.com.lighthost.kotlinDactylApi.api.client.models.SystemPermissionsKeyModel
import br.com.lighthost.kotlinDactylApi.api.client.models.SystemPermissionsModel
import br.com.lighthost.kotlinDactylApi.client.account.ClientAccountManager
import br.com.lighthost.kotlinDactylApi.client.server.ClientServer
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ClientApi (baseUrl:String, apiKey:String) {

    private val baseRequest = BaseRequest("$baseUrl/api/client/", apiKey)

    val user = ClientAccountManager(baseRequest)

    fun retrieveServerByIdentifier(id:String): ClientServer {
        return ClientServer(baseRequest.executeRequest(ClientRoutes.SERVER.getServer(id), null), baseRequest)
    }

    fun retrieveServers(): List<ClientServer>{
        val list:MutableList<ClientServer> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.SERVER.getServers(), null)).getJSONArray("data").forEach{
            it as JSONObject
            list.add(ClientServer(it.toString(), baseRequest))
        }
        return list
    }

    fun retrieveAssignablePermissions():List<SystemPermissionsModel> {
        val list:MutableList<SystemPermissionsModel> = mutableListOf()
        val data = JSONObject(baseRequest.executeRequest(ClientRoutes.PERMISSIONS.getSystemPermissions(), null)).getJSONObject("attributes").getJSONObject("permissions")
        data.names().forEach{ permissionName ->
           permissionName as String
           val description = data.getJSONObject(permissionName).getString("description")
           val jsonKeyList = data.getJSONObject(permissionName).getJSONObject("keys")
           val keyList:MutableList<SystemPermissionsKeyModel> = mutableListOf()
           jsonKeyList.names().forEach {
               it as String
               keyList.add(SystemPermissionsKeyModel(it, jsonKeyList.getString(it)))
           }
           list.add(SystemPermissionsModel(permissionName, description, keyList ))
       }
        return list
    }

}