package kotlinDactyl.requests.RouteModels

import kotlinDactyl.requests.routes.models.RouteModel

class ClientRoutes {

    internal object SERVER
    {
        fun serverDetails(serverid: String): RouteModel {return RouteModel("GET", "servers/${serverid}", "application/json")}
    }

    internal object FILEMANAGER
        {
            fun listfiles(serverid: String, directory: String): RouteModel {return RouteModel("GET", "servers/${serverid}/files/list?directory=${directory}", "application/json")}
            fun getcontents(serverid: String, file:String):RouteModel{return RouteModel("GET", "servers/${serverid}/files/contents?file=${file}", "application/json")}
            fun downloadfile(serverid: String, file: String):RouteModel{return RouteModel("GET", "servers/${serverid}/files/download?file=${file}", "application/json")}
            fun renamefiles(serverid: String):RouteModel{return RouteModel("PUT", "servers/${serverid}/files/rename", "application/json")}
            fun copyfile(serverid: String):RouteModel{return RouteModel("POST", "servers/${serverid}/files/copy", "application/json")}
            fun writefile(serverid: String, file:String):RouteModel{return RouteModel("POST", "servers/${serverid}/files/write?file=${file}", "text/plain")}
            fun compressfiles(serverid: String):RouteModel{return RouteModel("POST", "servers/${serverid}/files/compress", "application/json")}
            fun decompressfile(serverid: String):RouteModel{return RouteModel("POST", "servers/${serverid}/files/decompress", "application/json")}
            fun deletefiles(serverid: String):RouteModel{return RouteModel("POST", "servers/${serverid}/files/delete", "application/json")}
            fun createfolder(serverid: String):RouteModel{return RouteModel("POST", "servers/${serverid}/files/create-folder", "application/json")}
            fun uploadfile(serverid: String):RouteModel{return RouteModel("GET", "servers/${serverid}/files/upload", "application/json")}
            fun uploadfilebyurl(serverid: String, directory: String, url:String):RouteModel{return RouteModel("POST", "servers/${serverid}/files/pull?directory=${directory}&url=${url}", "application/json")}
        }

        internal object DATABASEMANAGER
        {
            fun listdatabases(serverid: String):RouteModel{return RouteModel("GET", "servers/${serverid}/databases", "application/json")}
            fun createdatabase(serverid: String):RouteModel{return RouteModel("POST", "servers/${serverid}/databases", "application/json")}
            fun rotatepassword(serverid: String, databaseid: String):RouteModel{return RouteModel("POST", "servers/${serverid}/databases/${databaseid}/rotate-password", "application/json")}
            fun deletedatabase(serverid: String, databaseid: String):RouteModel{return RouteModel("DELETE", "servers/${serverid}/databases/${databaseid}", "application/json")}

        }
    
}