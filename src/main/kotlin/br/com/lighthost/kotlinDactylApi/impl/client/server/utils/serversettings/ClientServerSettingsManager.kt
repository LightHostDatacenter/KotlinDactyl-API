package br.com.lighthost.kotlinDactylApi.impl.client.server.utils.serversettings

import br.com.lighthost.kotlinDactylApi.impl.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ClientServerSettingsManager(private val server : ClientServerDetails, private val baseRequest: BaseRequest) {

    fun getDockerImagesList(): MutableList<String> {
        val list: MutableList<String> = mutableListOf()
        val imagesArray = JSONObject(baseRequest.executeRequest(ClientRoutes.STARTUP.listVariables(server.attributes.identifier), null)).getJSONObject("meta").getJSONArray("docker_images")
        for (i:Int in 0 until imagesArray.length()){
            list.add(imagesArray.getString(i))
        }
        return list
    }

    fun setDockerImage(dockerImage:String){
        val json = JSONObject().accumulate("docker_image", dockerImage)
        baseRequest.executeRequest(ClientRoutes.SETTINGS.setDockerImage(server.attributes.identifier), json.toString())
    }

    fun renameServer(name:String){
        val json = JSONObject().accumulate("name", name)
        baseRequest.executeRequest(ClientRoutes.SETTINGS.renameServer(server.attributes.identifier), json.toString())
    }

    fun reinstallServer(){
        baseRequest.executeRequest(ClientRoutes.SETTINGS.reinstallServer(server.attributes.identifier), "")
    }

}