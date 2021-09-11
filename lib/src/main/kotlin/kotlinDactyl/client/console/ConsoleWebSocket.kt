package kotlinDactyl.client.console

import kotlinDactyl.client.ClientServer
import kotlinDactyl.client.console.models.ConsoleBackupModel
import kotlinDactyl.client.console.models.ConsoleCommander
import kotlinDactyl.client.console.models.ConsoleStatsModel
import kotlinDactyl.requests.BaseRequest
import kotlinDactyl.requests.RouteModels.ClientRoutes
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class ConsoleWebSocket(private val server: ClientServer, private val baseRequest: BaseRequest, private val handler:ConsoleHandler) : WebSocketListener() {

    private var wsDetails:WebSocketDetailsModel
    private var webSocket:WebSocket? = null
    private var commander:ConsoleCommander? = null

    init {
        wsDetails = getWssDetails()
        val client: OkHttpClient = OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build()
        val request: Request = Request.Builder().url(wsDetails.socketUrl).build()
        client.newWebSocket(request, this)
    }

    private data class WebSocketDetailsModel(var token:String, var socketUrl:String)

    private fun getWssDetails():WebSocketDetailsModel{
       val response = JSONObject(baseRequest.executeRequest(ClientRoutes.CONSOLE.getDetails(server.details.identifier), null)).getJSONObject("data")
        return WebSocketDetailsModel(response.getString("token"), response.getString("socket"))
    }

     override fun onOpen(webSocket: WebSocket, response: Response) {
         val args = JSONArray().put(wsDetails.token)
         val json = JSONObject().accumulate("event", "auth").put("args", args)
         this.webSocket = webSocket
         this.commander = ConsoleCommander(this, webSocket, server)
        webSocket.send(json.toString())
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
         val event = JSONObject(text).getString("event")
         lateinit var args:String

         if (JSONObject(text).optJSONArray("args") != null) {
             args = JSONObject(text).optJSONArray("args").first().toString()
         }

         when{
             event == "auth success" -> handler.onAuthSuccess(commander!!)
             event == "status" -> handler.onStatus(args)
             event == "console output" -> handler.onConsoleOutput(args)
             event == "daemon message" -> handler.onDaemonMessage(args)
             event == "daemon error" -> handler.onDaemonError(args)
             event == "install started" -> handler.onInstallStarted()
             event == "install output" -> handler.onInstallOutput(args)
             event == "install completed" -> handler.onInstallCompleted()
             event == "stats" -> handler.onStats(statsModelParser(args))
             event == "token expiring" -> renewToken(webSocket)
             event == "token expired" -> webSocket.cancel()
             event == "jwt error" -> webSocket.cancel()
             event == "transfer logs" -> handler.onTransferMessage(args)
             event == "transfer status" -> onTransferStatus(args, webSocket)
             event.contains("backup completed") -> handler.onBackupCompleted(backupModelParser(args))

         }
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        handler.onFailure(t)
    }

    private fun onTransferStatus(args:String, webSocket: WebSocket){
        if (args == "archived"){
            Thread.sleep(2000).run {
                webSocket.cancel()
                ConsoleWebSocket(server, baseRequest, handler)
                handler.onTransferSuccess()
            }
        }
    }

    private fun renewToken(webSocket: WebSocket){
        wsDetails = getWssDetails()
        val args = JSONArray().put(wsDetails.token)
        val json = JSONObject().accumulate("event", "auth").put("args", args)
        webSocket.send(json.toString())
    }

    private fun statsModelParser(data:String): ConsoleStatsModel {
        val json = JSONObject(data)
        return ConsoleStatsModel(
            json.getLong("memory_bytes"),
            json.getLong("memory_limit_bytes"),
            json.getDouble("cpu_absolute"),
            json.getJSONObject("network").getLong("rx_bytes"),
            json.getJSONObject("network").getLong("tx_bytes"),
            json.getString("state"),
            json.getLong("disk_bytes"))
    }

    private fun backupModelParser(data:String): ConsoleBackupModel {
        val json = JSONObject(data)
        return ConsoleBackupModel(
            json.getString("checksum"),
            json.getString("checksum_type"),
            json.getLong("file_size"),
            json.getBoolean("is_successful"),
            json.getString("uuid"))
    }


}