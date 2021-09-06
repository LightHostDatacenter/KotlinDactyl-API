package kotlinDactyl.requests.exceptions

import org.json.JSONObject

open class KotlinDactylException(message: String?) : RuntimeException(message)
{
    object Exceptions{

        class LoginException(message: String?) : KotlinDactylException(message)
        class NotFoundException(message: String?) : KotlinDactylException(message)
        class ServerException(message: String?) : KotlinDactylException(message)

        class MissingActionException(text: String, json: String) :
            KotlinDactylException(formatMessage(text, JSONObject(json))) {
            companion object {
                fun formatMessage(text: String, json: JSONObject): String {
                    val message = StringBuilder(
                        "'"+ text + "'".trimIndent())
                    for (o in json.getJSONArray("errors")) {
                        val obj = JSONObject(o.toString())
                        message.append("\t- ").append(obj.getString("detail")).append(" (Source: ")
                            .append(obj.getJSONObject("meta").getString("source_field")).append(")\n")
                    }
                    return message.toString()
                }
            }
        }
    }
}

