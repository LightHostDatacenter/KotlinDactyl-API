package br.com.lighthost.kotlinDactylApi.impl.client.account.actions

import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes

class ClientApiKeysActions(private val baseRequest: BaseRequest, private val keyId:String) {

    fun delete(){
        baseRequest.executeRequest(ClientRoutes.ACCOUNT.deleteApiKey(keyId), null)
    }

}