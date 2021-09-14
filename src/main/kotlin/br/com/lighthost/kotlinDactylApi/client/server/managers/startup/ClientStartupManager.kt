package br.com.lighthost.kotlinDactylApi.client.server.managers.startup

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.startup.models.EnvironmentVariableModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONObject

class ClientStartupManager(private val server: ClientServerDetails, private val baseRequest: BaseRequest) {

    fun getVariablesList():List<EnvironmentVariableModel>{
        val list: MutableList<EnvironmentVariableModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.STARTUP.listVariables(server.attributes.identifier), null)).getJSONArray("data").forEach {
            it as JSONObject
            list.add(clientVariableParser(it.getJSONObject("attributes").toString()))
        }
        return list
    }

    fun getVariable(name:String):EnvironmentVariableModel{
        return getVariablesList().first { it.envVariable == name }
    }

    private fun clientVariableParser(rawJson : String): EnvironmentVariableModel {
            val json = JSONObject(rawJson)
            return EnvironmentVariableModel(
                json.getString("name"),
                json.getString("description"),
                json.getString("env_variable"),
                json.getString("default_value"),
                json.getString("server_value"),
                json.getBoolean("is_editable"),
                json.getString("rules"),
                server,
                baseRequest)
    }

}