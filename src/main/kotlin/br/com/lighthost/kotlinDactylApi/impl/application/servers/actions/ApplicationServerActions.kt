package br.com.lighthost.kotlinDactylApi.impl.application.servers.actions

import br.com.lighthost.kotlinDactylApi.impl.application.servers.ApplicationServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes

class ApplicationServerActions (private val baseRequest: BaseRequest, private val server:ApplicationServerDetails) {

    fun reinstall(){
        baseRequest.executeRequest(ApplicationRoutes.SERVERS.reinstallServer(server.details.id), null)
    }

    fun suspend(){
        baseRequest.executeRequest(ApplicationRoutes.SERVERS.suspendServer(server.details.id), null)
    }

    fun unsuspend(){
        baseRequest.executeRequest(ApplicationRoutes.SERVERS.unsuspendServer(server.details.id), null)
    }

    fun delete(force: Boolean = false){
        if(force){
            baseRequest.executeRequest(ApplicationRoutes.SERVERS.forceDeleteServer(server.details.id), null)
        }
        else{
            baseRequest.executeRequest(ApplicationRoutes.SERVERS.deleteServer(server.details.id), null)
        }
    }

}