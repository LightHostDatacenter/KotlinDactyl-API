package br.com.lighthost.kotlinDactylApi.api.client

import br.com.lighthost.kotlinDactylApi.api.client.models.SystemPermissionsKeyModel
import br.com.lighthost.kotlinDactylApi.api.client.models.SystemPermissionsModel
import br.com.lighthost.kotlinDactylApi.client.account.ClientAccountManager
import br.com.lighthost.kotlinDactylApi.client.server.ClientServer
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
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
        val serversArray = JSONObject(baseRequest.executeRequest(ClientRoutes.SERVER.getServers(), null)).getJSONArray("data")
        for (i:Int in 0 until serversArray.length()){
            val obj = serversArray.getJSONObject(i)
            list.add(ClientServer(obj.toString(), baseRequest))
        }
        return list
    }

    fun retrieveAssignablePermissions():List<SystemPermissionsModel> {
        val list:MutableList<SystemPermissionsModel> = mutableListOf()
        val data = JSONObject(baseRequest.executeRequest(ClientRoutes.PERMISSIONS.getSystemPermissions(), null)).getJSONObject("attributes").getJSONObject("permissions")
        val permissionNames = data.names()
        for (i:Int in 0 until permissionNames.length()){
            val permissionName = permissionNames.getString(i)
            val description = data.getJSONObject(permissionName).getString("description")
            val jsonKeyListNames = data.getJSONObject(permissionName).getJSONObject("keys").names()
            val jsonKeyList = data.getJSONObject(permissionName).getJSONObject("keys")
            val keyList:MutableList<SystemPermissionsKeyModel> = mutableListOf()
            for (keyI:Int in 0 until jsonKeyListNames.length()){
                keyList.add(SystemPermissionsKeyModel(jsonKeyListNames[keyI].toString(), jsonKeyList.getString(jsonKeyListNames[keyI].toString())))
            }
            list.add(SystemPermissionsModel(permissionName, description, keyList ))
        }
        return list
    }

}