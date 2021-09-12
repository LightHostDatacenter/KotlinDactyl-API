package br.com.lighthost.kotlinDactylApi.requests

import br.com.lighthost.kotlinDactylApi.requests.exceptions.KotlinDactylException
import br.com.lighthost.kotlinDactylApi.requests.routes.models.RouteModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

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
                    403 -> throw KotlinDactylException.Exceptions.LoginException("The provided token is either incorrect or does not have access to process this request.")
                    404 -> throw KotlinDactylException.Exceptions.NotFoundException("The requested entity was not found.")
                    422 -> throw KotlinDactylException.Exceptions.MissingActionException("The request is missing required fields.", response.body!!.string())
                    500 -> throw KotlinDactylException.Exceptions.ServerException("The server has encountered an Internal Server Error.")
                    else -> throw KotlinDactylException.Exceptions.ServerException(java.lang.String.format("KotlinDactyl encountered an error. Request responsecode: ${response.code} Content: ${response.body!!.string()}"))
                }
            }
            else{ return response.body!!.string() }
        }
    }
}