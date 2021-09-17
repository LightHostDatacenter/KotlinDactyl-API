package br.com.lighthost.kotlinDactylApi.application.nests.models

import br.com.lighthost.kotlinDactylApi.application.nests.actions.ApplicationNestActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import java.time.OffsetDateTime

data class ApplicationNestModel(
    val id:Int,
    val uuid:String,
    val author:String,
    val name:String,
    val description:String,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime,
    private val baseRequest: BaseRequest){

    fun actions(): ApplicationNestActions {
        return ApplicationNestActions(baseRequest,id)
    }

}
