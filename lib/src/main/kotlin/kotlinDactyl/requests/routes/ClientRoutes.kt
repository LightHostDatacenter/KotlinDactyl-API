package kotlinDactyl.requests.RouteModels

import kotlinDactyl.requests.routes.models.RouteModel

class ClientRoutes {

    internal object SERVER
    {
        fun serverDetails(serverId: String): RouteModel {return RouteModel("GET", "servers/${serverId}", "application/json")}
    }

     object FILES
        {
            fun listFiles(serverId: String, directory: String): RouteModel {return RouteModel("GET", "servers/${serverId}/files/list?directory=${directory}", "application/json")}
            fun getContents(serverId: String, file:String):RouteModel{return RouteModel("GET", "servers/${serverId}/files/contents?file=${file}", "application/json")}
            fun downloadFile(serverId: String, file: String):RouteModel{return RouteModel("GET", "servers/${serverId}/files/download?file=${file}", "application/json")}
            fun renameFile(serverId: String):RouteModel{return RouteModel("PUT", "servers/${serverId}/files/rename", "application/json")}
            fun copyFile(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/files/copy", "application/json")}
            fun writeFile(serverId: String, file:String):RouteModel{return RouteModel("POST", "servers/${serverId}/files/write?file=${file}", "text/plain")}
            fun compressFiles(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/files/compress", "application/json")}
            fun decompressFile(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/files/decompress", "application/json")}
            fun deleteFiles(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/files/delete", "application/json")}
            fun createFolder(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/files/create-folder", "application/json")}
            fun uploadFile(serverId: String):RouteModel{return RouteModel("GET", "servers/${serverId}/files/upload", "application/json")}
            fun uploadFileByUrl(serverId: String, directory: String, url:String):RouteModel{return RouteModel("POST", "servers/${serverId}/files/pull?directory=${directory}&url=${url}", "application/json")}
            fun chmodFile(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/files/chmod", "application/json")}
        }

        internal object DATABASES
        {
            fun listDatabases(serverId: String):RouteModel{return RouteModel("GET", "servers/${serverId}/databases", "application/json")}
            fun createDatabase(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/databases", "application/json")}
            fun rotatePassword(serverId: String, databaseid: String):RouteModel{return RouteModel("POST", "servers/${serverId}/databases/${databaseid}/rotate-password", "application/json")}
            fun deleteDatabase(serverId: String, databaseid: String):RouteModel{return RouteModel("DELETE", "servers/${serverId}/databases/${databaseid}", "application/json")}
        }
}