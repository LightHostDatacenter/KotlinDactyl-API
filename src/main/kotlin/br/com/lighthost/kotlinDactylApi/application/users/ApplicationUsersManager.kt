package br.com.lighthost.kotlinDactylApi.application.users

import br.com.lighthost.kotlinDactylApi.application.users.models.ApplicationNewUserModel
import br.com.lighthost.kotlinDactylApi.application.users.models.ApplicationUserModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ApplicationUsersManager (private val baseRequest: BaseRequest) {

    fun retrieveUsers():List<ApplicationUserModel>{
        val list: MutableList<ApplicationUserModel> = mutableListOf()
        val usersArray = JSONObject(baseRequest.executeRequest(ApplicationRoutes.USERS.getUsers(), null)).getJSONArray("data")
        for (i:Int in 0 until usersArray.length()){
            val obj = usersArray.getJSONObject(i)
            list.add(userParser(obj.getJSONObject("attributes")))
        }
        return list
    }

    fun retrieveUserById(id:Int):ApplicationUserModel{
        return userParser(JSONObject(baseRequest.executeRequest(ApplicationRoutes.USERS.getUser(id), null)).getJSONObject("attributes"))
    }

    fun retrieveUserByExternalId(externalId:String):ApplicationUserModel{
        return userParser(JSONObject(baseRequest.executeRequest(ApplicationRoutes.USERS.getUserByExternalId(externalId), null)).getJSONObject("attributes"))
    }

    fun createUser(newUser:ApplicationNewUserModel): ApplicationUserModel {
        val json = JSONObject().accumulate("email", newUser.email)
            .accumulate("username", newUser.username).accumulate("first_name", newUser.firstName)
            .accumulate("last_name", newUser.lastName).accumulate("external_id", newUser.externalId)
            .accumulate("root_admin", newUser.isRootAdmin).accumulate("language", newUser.language)
        return userParser(JSONObject(baseRequest.executeRequest(
            ApplicationRoutes.USERS.createUser(), json.toString())).getJSONObject("attributes"))
    }

    private fun userParser(json : JSONObject): ApplicationUserModel {
        return ApplicationUserModel(
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
            baseRequest)
    }

}