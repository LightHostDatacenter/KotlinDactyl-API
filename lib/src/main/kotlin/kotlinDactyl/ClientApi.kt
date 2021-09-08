package kotlinDactyl

import kotlinDactyl.client.ClientServer
import kotlinDactyl.client.account.ClientAccountManager
import kotlinDactyl.client.account.models.ClientAccountDetailsModel
import kotlinDactyl.requests.BaseRequest
import kotlinDactyl.requests.RouteModels.ClientRoutes

class ClientApi (baseUrl:String, apiKey:String) {

    private val baseRequest = BaseRequest(baseUrl, apiKey)

    val user = ClientAccountManager(baseRequest)

    fun retrieveServerByIdentifier(id:String): ClientServer {
        return ClientServer(baseRequest.executeRequest(ClientRoutes.SERVER.serverDetails(id), null), baseRequest)
    }
}