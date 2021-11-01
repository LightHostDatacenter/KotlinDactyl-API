package br.com.lighthost.kotlinDactylApi.requests

import br.com.lighthost.kotlinDactylApi.requests.exceptions.KotlinDactylException
import br.com.lighthost.kotlinDactylApi.requests.routes.models.RouteModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.*

class BaseRequest(private val baseUrl:String, private val apiKey:String) {

    private val webClient = OkHttpClient()

    fun executeRequest(routeModel:RouteModel, json:String?):String {
        var jsonBody = json
        if(json == null){
            jsonBody = ""
        }

        val request : Request

        if (routeModel.type == "PUT"){
            request = Request.Builder()
                .url(baseUrl + routeModel.endpoint)
                .addHeader("Authorization", "Bearer $apiKey")
                .addHeader("Accept", "application/json")
                .put(jsonBody!!.toRequestBody(routeModel.contentType.toMediaType()))
                .build()
        }
        else if (routeModel.type == "POST"){
            request = Request.Builder()
                .url(baseUrl + routeModel.endpoint)
                .addHeader("Authorization", "Bearer $apiKey")
                .addHeader("Accept", "application/json")
                .post(jsonBody!!.toRequestBody(routeModel.contentType.toMediaType()))
                .build()
        }
        else if (routeModel.type == "PATCH"){
            request = Request.Builder()
                .url(baseUrl + routeModel.endpoint)
                .addHeader("Authorization", "Bearer $apiKey")
                .addHeader("Accept", "application/json")
                .patch(jsonBody!!.toRequestBody(routeModel.contentType.toMediaType()))
                .build()
        }
        else if (routeModel.type == "DELETE"){
            request = Request.Builder()
                .url(baseUrl + routeModel.endpoint)
                .addHeader("Authorization", "Bearer $apiKey")
                .addHeader("Accept", "application/json")
                .delete(jsonBody!!.toRequestBody(routeModel.contentType.toMediaType()))
                .build()
        }
        else{
            request = Request.Builder()
                .url(baseUrl + routeModel.endpoint)
                .addHeader("Authorization", "Bearer $apiKey")
                .addHeader("Accept", "application/json")
                .build()
        }

        webClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                when (response.code) {
                    403 -> throw KotlinDactylException.Exceptions.LoginException(JSONObject().accumulate("error_code", response.code).accumulate("error_message", "The provided token is either incorrect or does not have access to process this request.").toString())
                    404 -> throw KotlinDactylException.Exceptions.NotFoundException(JSONObject().accumulate("error_code", response.code).accumulate("error_message", "The requested entity was not found.").toString())
                    422 -> throw KotlinDactylException.Exceptions.MissingActionException(JSONObject().accumulate("error_code", response.code).accumulate("error_message", "The request is missing required fields.").accumulate("error_content:", Base64.getEncoder().encodeToString(response.body!!.string().toByteArray())).toString())
                    500 -> throw KotlinDactylException.Exceptions.ServerException(JSONObject().accumulate("error_code", response.code).accumulate("error_message", "The server has encountered an Internal Server Error.").toString())
                    else -> throw KotlinDactylException.Exceptions.ServerException(JSONObject().accumulate("error_code", response.code).accumulate("error_message",  "KotlinDactyl encountered an error. Request responsecode ${response.code}").accumulate("error_content:", Base64.getEncoder().encodeToString(response.body!!.string().toByteArray())).toString())
                }
            }
            else{ return response.body!!.string() }
        }
    }
}