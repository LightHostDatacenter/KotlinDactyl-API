package br.com.lighthost.kotlinDactylApi.application.locations.actions

import br.com.lighthost.kotlinDactylApi.application.locations.models.ApplicationLocationModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject

class ApplicationLocationActions (private val baseRequest: BaseRequest, private val location:ApplicationLocationModel) {

    fun update(){
        val json = JSONObject().accumulate("short", location.shortDescription).accumulate("long", location.longDescription)
        baseRequest.executeRequest(ApplicationRoutes.LOCATIONS.updateLocation(location.id), json.toString())
    }

    fun delete(){
        baseRequest.executeRequest(ApplicationRoutes.LOCATIONS.deleteLocation(location.id),null)
    }

}