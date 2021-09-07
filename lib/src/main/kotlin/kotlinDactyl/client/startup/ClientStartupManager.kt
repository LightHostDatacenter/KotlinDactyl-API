package kotlinDactyl.client.startup

import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.client.startup.models.EnviromentVariableModel
import kotlinDactyl.requests.BaseRequest
import kotlinDactyl.requests.RouteModels.ClientRoutes
import org.json.JSONObject

class ClientStartupManager(private val server: ClientServerDetails, private val baseRequest: BaseRequest) {

    fun getVariablesList():List<EnviromentVariableModel>{
        val list: MutableList<EnviromentVariableModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.STARTUP.listVariables(server.identifier), null)).getJSONArray("data").forEach {
            it as JSONObject
            list.add(ClientVariableParser.parse(it.getJSONObject("attributes").toString()))
        }
        return list
    }

    fun getVariable(name:String):EnviromentVariableModel{
        return getVariablesList().first { it.envVariable == name }
    }

    fun updateVariable(variableName:String, variableValue:String): EnviromentVariableModel {
        val json = JSONObject().accumulate("key", variableName).accumulate("value", variableValue)
        return ClientVariableParser.parse(
            JSONObject(baseRequest.executeRequest(ClientRoutes.STARTUP.updateVariable(server.identifier),json.toString()))
                .getJSONObject("attributes").toString())
    }

    object ClientVariableParser {
        fun parse(rawJson : String): EnviromentVariableModel {
            val json = JSONObject(rawJson)
            return EnviromentVariableModel(
                json.getString("name"),
                json.getString("description"),
                json.getString("env_variable"),
                json.getString("default_value"),
                json.getString("server_value"),
                json.getBoolean("is_editable"),
                json.getString("rules"))
        }
    }

}