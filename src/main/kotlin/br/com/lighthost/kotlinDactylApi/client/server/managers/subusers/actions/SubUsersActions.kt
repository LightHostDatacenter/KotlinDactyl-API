package br.com.lighthost.kotlinDactylApi.client.server.managers.subusers.actions

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class SubUsersActions (private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val userUuid:String) {

    fun updatePermissions(newPermissions:List<String>){
        val json = JSONObject().accumulate("permissions", newPermissions)
        baseRequest.executeRequest(ClientRoutes.SUBUSERS.updateSubUser(server.attributes.identifier, userUuid), json.toString())
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.SUBUSERS.deleteSubUser(server.attributes.identifier, userUuid), null)
    }

}