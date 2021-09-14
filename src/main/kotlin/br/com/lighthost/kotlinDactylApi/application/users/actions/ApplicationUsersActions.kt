package br.com.lighthost.kotlinDactylApi.application.users.actions

import br.com.lighthost.kotlinDactylApi.application.users.models.ApplicationUserModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject

class ApplicationUsersActions(private val baseRequest: BaseRequest, private val userUpdate:ApplicationUserModel) {

    fun update(){
        val json = JSONObject()
            .accumulate("email", userUpdate.email)
            .accumulate("username", userUpdate.username)
            .accumulate("first_name", userUpdate.firstName)
            .accumulate("last_name", userUpdate.lastName)
            .accumulate("external_id", (if (userUpdate.externalId == null) "" else userUpdate.externalId))
            .accumulate("root_admin", userUpdate.isRootAdmin)
        baseRequest.executeRequest(ApplicationRoutes.USERS.updateUser(userUpdate.id), json.toString())
    }

    fun delete(){
        baseRequest.executeRequest(ApplicationRoutes.USERS.deleteUser(userUpdate.id), null)
    }

}