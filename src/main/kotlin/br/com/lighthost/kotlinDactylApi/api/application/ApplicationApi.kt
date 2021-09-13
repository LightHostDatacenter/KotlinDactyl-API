package br.com.lighthost.kotlinDactylApi.api.application

import br.com.lighthost.kotlinDactylApi.application.users.ApplicationUsersManager
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest

class ApplicationApi (baseUrl:String, apiKey:String) {

    private val baseRequest = BaseRequest("$baseUrl/api/application/", apiKey)

    val userManager = ApplicationUsersManager(baseRequest)

}