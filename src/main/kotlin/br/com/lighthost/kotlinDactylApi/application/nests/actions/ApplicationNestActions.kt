package br.com.lighthost.kotlinDactylApi.application.nests.actions

import br.com.lighthost.kotlinDactylApi.application.nests.models.ApplicationEggModel
import br.com.lighthost.kotlinDactylApi.application.nests.models.ApplicationEggVariableModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ApplicationNestActions(private val baseRequest: BaseRequest, private val nestId:Int) {

    fun retrieveEggs(): MutableList<ApplicationEggModel> {
        val eggsList : MutableList<ApplicationEggModel> = mutableListOf()
        val eggsArray =  JSONObject(baseRequest.executeRequest(ApplicationRoutes.EGGS.getEggs(nestId), null)).getJSONArray("data")
        for (i:Int in 0 until eggsArray!!.length()){
            val it = eggsArray.getJSONObject(i).getJSONObject("attributes")
            eggsList.add(parseEggModel(it))
        }
        return eggsList
    }

    fun retrieveEggById(id:Int): ApplicationEggModel {
        return parseEggModel(JSONObject(baseRequest.executeRequest(ApplicationRoutes.EGGS.getEgg(nestId,id), null)).getJSONObject("attributes"))
    }

    private fun parseEggModel(json: JSONObject): ApplicationEggModel {
        val variableList:MutableList<ApplicationEggVariableModel> = mutableListOf()
        val variablesArray = json.getJSONObject("relationships").getJSONObject("variables").getJSONArray("data")
        for (i:Int in 0 until variablesArray.length()){
            val variableObj = variablesArray[i] as JSONObject
            val variable = variableObj.getJSONObject("attributes")
            variableList.add(ApplicationEggVariableModel(
                variable.getString("name"),
                variable.getString("description"),
                variable.getString("env_variable"),
                variable.getString("default_value"),
                variable.getBoolean("user_viewable"),
                variable.getBoolean("user_editable"),
                variable.getString("rules"),
                OffsetDateTime.parse(variable.getString("created_at")),
                OffsetDateTime.parse(variable.getString("updated_at"))))
        }
        val dockerList: MutableList<String> = mutableListOf()
        val dockerArray = json.getJSONArray("docker_images")
        for (i:Int in 0 until dockerArray.length()){
            dockerList.add(dockerArray.getString(i))
        }
        return ApplicationEggModel(
            json.getInt("id"),
            json.getString("uuid"),
            json.getString("author"),
            json.getString("name"),
            json.optString("description"),
            json.getInt("nest"),
            json.getString("docker_image"),
            dockerList,
            json.getJSONObject("config"),
            json.getString("startup"),
            json.getJSONObject("script"),
            variableList,
            OffsetDateTime.parse(json.getString("created_at")),
            OffsetDateTime.parse(json.getString("updated_at")))
    }

}