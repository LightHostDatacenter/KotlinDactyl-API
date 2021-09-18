package br.com.lighthost.kotlinDactylApi.impl.application.locations.models

import br.com.lighthost.kotlinDactylApi.impl.application.locations.actions.ApplicationLocationActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import java.time.OffsetDateTime

data class ApplicationLocationModel(
    val id:Int,
    var shortDescription:String,
    var longDescription:String?,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime,
    val baseRequest: BaseRequest){

    fun actions(): ApplicationLocationActions {
        return ApplicationLocationActions(baseRequest, this)
    }

}
