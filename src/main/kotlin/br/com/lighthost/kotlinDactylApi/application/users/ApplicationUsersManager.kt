package br.com.lighthost.kotlinDactylApi.application.users

import br.com.lighthost.kotlinDactylApi.application.users.actions.ApplicationUsersActions
import br.com.lighthost.kotlinDactylApi.application.users.models.UserModel
import br.com.lighthost.kotlinDactylApi.application.users.models.UserUpdateModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ApplicationUsersManager (private val baseRequest: BaseRequest) {

    fun retrieveUsers():List<UserModel>{
        val list: MutableList<UserModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ApplicationRoutes.USERS.getUsers(), null)).getJSONArray("data").forEach {
            it as JSONObject
            list.add(userParser(it.getJSONObject("attributes")))
        }
        return list
    }

    fun retrieveUserById(id:Int):UserModel{
        return userParser(JSONObject(baseRequest.executeRequest(ApplicationRoutes.USERS.getUser(id), null)).getJSONObject("attributes"))
    }

    fun retrieveUserByExternalId(externalId:String):UserModel{
        return userParser(JSONObject(baseRequest.executeRequest(ApplicationRoutes.USERS.getUserByExternalId(externalId), null)).getJSONObject("attributes"))
    }

    fun createUser(newUser:UserUpdateModel): UserModel {
        val json = JSONObject().accumulate("email", newUser.email)
            .accumulate("username", newUser.username).accumulate("first_name", newUser.firstName)
            .accumulate("last_name", newUser.lastName).accumulate("external_id", newUser.externalId)
            .accumulate("root_admin", newUser.isRootAdmin).accumulate("language", newUser.language)
        return userParser(JSONObject(baseRequest.executeRequest(
            ApplicationRoutes.USERS.createUser(), json.toString())).getJSONObject("attributes"))
    }

    private fun userParser(json : JSONObject): UserModel {
        val updateModel = UserUpdateModel(
            json.getString("email"),
            json.getString("username"),
            json.getString("first_name"),
            json.getString("last_name"),
            json.optString("external_id"),
            json.getBoolean("root_admin"),
            json.getString("language"))
        return UserModel(
            json.getInt("id"),
            json.optString("external_id"),
            json.getString("uuid"),
            json.getString("username"),
            json.getString("email"),
            json.getString("first_name"),
            json.getString("last_name"),
            json.getString("language"),
            json.getBoolean("root_admin"),
            json.getBoolean("2fa"),
            OffsetDateTime.parse(json.getString("created_at")),
            OffsetDateTime.parse(json.getString("updated_at")),
            updateModel,
            ApplicationUsersActions(baseRequest, json.getInt("id")))
    }

}