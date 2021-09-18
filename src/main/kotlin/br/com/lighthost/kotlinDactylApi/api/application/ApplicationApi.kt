package br.com.lighthost.kotlinDactylApi.api.application

import br.com.lighthost.kotlinDactylApi.impl.application.locations.ApplicationLocationsManager
import br.com.lighthost.kotlinDactylApi.impl.application.nests.ApplicationNestManager
import br.com.lighthost.kotlinDactylApi.impl.application.nodes.ApplicationNodeManager
import br.com.lighthost.kotlinDactylApi.impl.application.servers.ApplicationServerManager
import br.com.lighthost.kotlinDactylApi.impl.application.users.ApplicationUsersManager
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest

class ApplicationApi (baseUrl:String, apiKey:String) {

    private val baseRequest = BaseRequest("$baseUrl/api/application/", apiKey)

    val userManager = ApplicationUsersManager(baseRequest)
    val locationManager = ApplicationLocationsManager(baseRequest)
    val nodeManager = ApplicationNodeManager(baseRequest)
    val nestManager = ApplicationNestManager(baseRequest)
    val serverManager = ApplicationServerManager(baseRequest)

}