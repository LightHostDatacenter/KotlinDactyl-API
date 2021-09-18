package br.com.lighthost.kotlinDactylApi.impl.application.servers.models

import br.com.lighthost.kotlinDactylApi.impl.application.servers.ApplicationServerDetails
import br.com.lighthost.kotlinDactylApi.impl.application.servers.actions.ApplicationServerDatabaseActions
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import java.time.OffsetDateTime

data class ApplicationServerDatabaseModel(
    val id:Int,
    val server:Int,
    val host:Int,
    val databaseName:String,
    val databaseUsername:String,
    val allowedNetwork:String,
    var maxConnections:Int,
    val createdAt:OffsetDateTime,
    val updatedAt:OffsetDateTime,
    private val baseRequest: BaseRequest,
    private val serverDetail: ApplicationServerDetails){

    fun actions(): ApplicationServerDatabaseActions {
        return ApplicationServerDatabaseActions(baseRequest, serverDetail, id)
    }

}
