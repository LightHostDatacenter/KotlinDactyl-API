package br.com.lighthost.kotlinDactylApi.application.nodes.actions

import br.com.lighthost.kotlinDactylApi.application.nodes.models.ApplicationAllocationModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes

class ApplicationAllocationActions(private val baseRequest: BaseRequest, private val nodeId: Int, private val allocation:ApplicationAllocationModel) {

    fun delete(){
        baseRequest.executeRequest(ApplicationRoutes.ALLOCATIONS.deleteAllocation(nodeId, allocation.id), null)
    }

}