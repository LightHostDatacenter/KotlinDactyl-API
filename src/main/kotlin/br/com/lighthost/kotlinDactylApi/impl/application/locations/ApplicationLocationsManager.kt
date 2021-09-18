package br.com.lighthost.kotlinDactylApi.impl.application.locations

import br.com.lighthost.kotlinDactylApi.impl.application.locations.models.ApplicationLocationModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ApplicationLocationsManager(private val baseRequest: BaseRequest) {

    fun retrieveLocations(): MutableList<ApplicationLocationModel> {
        val list:MutableList<ApplicationLocationModel> = mutableListOf()
        val locationsArray = JSONObject(baseRequest.executeRequest(ApplicationRoutes.LOCATIONS.getLocations(), null)).getJSONArray("data")
        for (i:Int in 0 until locationsArray.length()){
            val obj = locationsArray.getJSONObject(i)
            list.add(locationParser(obj.getJSONObject("attributes")))
        }
        return list
    }

    fun retrieveLocationById(id:Int): ApplicationLocationModel {
        return locationParser(JSONObject(baseRequest.executeRequest(ApplicationRoutes.LOCATIONS.getLocation(id), null)).getJSONObject("attributes"))
    }

    fun createLocation(short:String, long:String?): ApplicationLocationModel {
        val json = JSONObject().accumulate("short", short).accumulate("long", long)
        return locationParser(JSONObject(baseRequest.executeRequest(ApplicationRoutes.LOCATIONS.createLocation(),json.toString())).getJSONObject("attributes"))
    }

    private fun locationParser(json : JSONObject): ApplicationLocationModel {
        return ApplicationLocationModel(
            json.getInt("id"),
            json.getString("short"),
            json.optString("long"),
            OffsetDateTime.parse(json.getString("created_at")),
            OffsetDateTime.parse(json.getString("updated_at")),
            baseRequest)
    }

}