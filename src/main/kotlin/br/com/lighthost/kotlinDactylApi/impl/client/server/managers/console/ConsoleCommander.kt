package br.com.lighthost.kotlinDactylApi.impl.client.server.managers.console

import br.com.lighthost.kotlinDactylApi.impl.client.server.ClientServer
import okhttp3.WebSocket
import okio.ByteString
import org.json.JSONArray
import org.json.JSONObject
import java.sql.Time
import java.time.Instant
import java.util.*

class ConsoleCommander (private val console: ConsoleWebSocket, private val webSocket: WebSocket, private val clientServer: ClientServer) {

    inner class Server {
        fun backup() {clientServer.backupManager.createBackup(Time.from(Instant.now()).toString(), null, false)}
        fun reinstall() { clientServer.settings.reinstallServer() }
        fun restart() { webSocketSend("set state", "restart") }
        fun stop() { webSocketSend("set state", "stop") }
        fun start() { webSocketSend("set state", "start") }
        fun kill() { webSocketSend("set state", "kill") }
    }

    inner class Console {
        fun sendCommand(command: String) { webSocketSend("send command", command) }
        fun requestLogs() { webSocketSend("send logs", null) }
        fun requestStats() { webSocketSend("send stats", null) }
    }

    inner class Socket {
        fun shutdown(){ webSocket.close(1000, "Client shutting down") }
        fun sendRaw(message:String){webSocket.send(message)}
        fun sendRaw(message:ByteString){webSocket.send(message)}
    }

    private fun webSocketSend(event: String, payload: String?) {
        val args = JSONArray().put(payload)
        val json = JSONObject().accumulate("event", event).put("args", args)
        webSocket.send(json.toString())
    }

}


