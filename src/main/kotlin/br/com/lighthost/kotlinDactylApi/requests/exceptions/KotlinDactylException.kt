package br.com.lighthost.kotlinDactylApi.requests.exceptions

import org.json.JSONObject

open class KotlinDactylException(message: String?) : RuntimeException(message)
{
    object Exceptions{

        class LoginException(message: String?) : KotlinDactylException(message)
        class NotFoundException(message: String?) : KotlinDactylException(message)
        class ServerException(message: String?) : KotlinDactylException(message)
        class MissingActionException(message: String?) : KotlinDactylException(message)

    }
}

