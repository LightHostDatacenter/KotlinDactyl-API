package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.subusers.actions

import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.subusers.models.ClientSubUserModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class SubUsersActions (private val server: ClientServerDetails, private val baseRequest: BaseRequest, private val subUser:ClientSubUserModel) {

    fun update(){
        val json = JSONObject().accumulate("permissions", subUser.permissions)
        baseRequest.executeRequest(ClientRoutes.SUBUSERS.updateSubUser(server.attributes.identifier, subUser.uuid), json.toString())
    }

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.SUBUSERS.deleteSubUser(server.attributes.identifier, subUser.uuid), null)
    }

}