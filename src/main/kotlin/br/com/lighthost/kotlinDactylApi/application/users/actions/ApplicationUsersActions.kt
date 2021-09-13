package br.com.lighthost.kotlinDactylApi.application.users.actions

import br.com.lighthost.kotlinDactylApi.application.users.models.UserUpdateModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject

class ApplicationUsersActions(private val baseRequest: BaseRequest, private val userId:Int) {

    fun update(userUpdate: UserUpdateModel){
        val json = JSONObject()
            .accumulate("email", userUpdate.email)
            .accumulate("username", userUpdate.username)
            .accumulate("first_name", userUpdate.firstName)
            .accumulate("last_name", userUpdate.lastName)
            .accumulate("external_id", userUpdate.externalId!!)
        baseRequest.executeRequest(ApplicationRoutes.USERS.updateUser(userId), json.toString())
    }

    fun delete(){
        baseRequest.executeRequest(ApplicationRoutes.USERS.deleteUser(userId), null)
    }

}