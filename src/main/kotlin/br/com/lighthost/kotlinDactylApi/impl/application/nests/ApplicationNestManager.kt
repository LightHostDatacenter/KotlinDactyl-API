package br.com.lighthost.kotlinDactylApi.impl.application.nests

import br.com.lighthost.kotlinDactylApi.impl.application.nests.models.ApplicationNestModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ApplicationNestManager(private val baseRequest: BaseRequest) {

    fun retrieveNests(): MutableList<ApplicationNestModel> {
        val nestsList: MutableList<ApplicationNestModel> = mutableListOf()
        val nestsArray =  JSONObject(baseRequest.executeRequest(ApplicationRoutes.NESTS.getNests(), null)).getJSONArray("data")
        for (i:Int in 0 until nestsArray!!.length()){
            val it = nestsArray.getJSONObject(i)
            nestsList.add(parseNestModel(it.getJSONObject("attributes")))
        }
        return nestsList
    }

    fun retrieveNestById(id:Int): ApplicationNestModel {
        return parseNestModel(JSONObject(baseRequest.executeRequest(ApplicationRoutes.NESTS.getNest(id), null)).getJSONObject("attributes"))
    }

    private fun parseNestModel(json: JSONObject): ApplicationNestModel {
        return ApplicationNestModel(
            json.getInt("id"),
            json.getString("uuid"),
            json.getString("author"),
            json.getString("name"),
            json.getString("description"),
            OffsetDateTime.parse(json.getString("created_at")),
            OffsetDateTime.parse(json.getString("updated_at")),
            baseRequest)
    }

}