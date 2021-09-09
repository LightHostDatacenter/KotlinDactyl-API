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

    object DATABASES
        {
            fun listDatabases(serverId: String):RouteModel{return RouteModel("GET", "servers/${serverId}/databases?include=password", "application/json")}
            fun createDatabase(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/databases", "application/json")}
            fun rotatePassword(serverId: String, databaseId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/databases/${databaseId}/rotate-password", "application/json")}
            fun deleteDatabase(serverId: String, databaseId: String):RouteModel{return RouteModel("DELETE", "servers/${serverId}/databases/${databaseId}", "application/json")}
        }

    object NETWORK
    {
        fun listAllocations(serverId: String):RouteModel{return RouteModel("GET", "servers/${serverId}/network/allocations", "application/json")}
        fun assignAllocation(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/network/allocations", "application/json")}
        fun setAllocationNote(serverId: String, allocationId: Int):RouteModel{return RouteModel("POST", "servers/${serverId}/network/allocations/${allocationId}", "application/json")}
        fun setPrimaryAllocation(serverId: String, allocationId: Int):RouteModel{return RouteModel("POST", "servers/${serverId}/network/allocations/${allocationId}/primary", "application/json")}
        fun deleteAllocation(serverId: String, allocationId: Int):RouteModel{return RouteModel("DELETE", "servers/${serverId}/network/allocations/${allocationId}", "application/json")}
    }

    object BACKUPS
    {
        fun listBackups(serverId: String):RouteModel{return RouteModel("GET", "servers/${serverId}/backups", "application/json")}
        fun createBackup(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/backups", "application/json")}
        fun toggleLock(serverId: String, backupUuid: String):RouteModel{return RouteModel("POST", "servers/${serverId}/backups/${backupUuid}/lock", "application/json")}
        fun getBackup(serverId: String, backupUuid:String):RouteModel{return RouteModel("GET", "servers/${serverId}/backups/${backupUuid}", "application/json")}
        fun getBackupDownload(serverId: String, backupUuid:String):RouteModel{return RouteModel("GET", "servers/${serverId}/backups/${backupUuid}/download", "application/json")}
        fun deleteBackup(serverId: String, backupUuid:String):RouteModel{return RouteModel("DELETE", "servers/${serverId}/backups/${backupUuid}", "application/json")}
    }

    object POWER
    {
        fun setServerPower(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/power", "application/json")}
    }

    object STARTUP
    {
        fun listVariables(serverId: String):RouteModel{return RouteModel("GET", "servers/${serverId}/startup", "application/json")}
        fun updateVariable(serverId: String):RouteModel{return RouteModel("PUT", "servers/${serverId}/startup/variable", "application/json")}
    }

    object SETTINGS
    {
        fun renameServer(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/settings/rename", "application/json")}
        fun reinstallServer(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/settings/reinstall", "application/json")}
    }

    object ACCOUNT
    {
        fun getDetails():RouteModel{return RouteModel("GET", "account", "application/json")}
        fun get2FADetails():RouteModel{return RouteModel("GET", "account/two-factor", "application/json")}
        fun enable2FA():RouteModel{return RouteModel("POST", "account/two-factor", "application/json")}
        fun disable2FA():RouteModel{return RouteModel("DELETE", "account/two-factor", "application/json")}
        fun updateEmail():RouteModel{return RouteModel("PUT", "account/email", "application/json")}
        fun updatePassword():RouteModel{return RouteModel("PUT", "account/password", "application/json")}
        fun getApiKeys():RouteModel{return RouteModel("GET", "account/api-keys", "application/json")}
        fun createApiKey():RouteModel{return RouteModel("POST", "account/api-keys", "application/json")}
        fun deleteApiKey(keyId:String):RouteModel{return RouteModel("DELETE", "account/api-keys/${keyId}", "application/json")}
    }

    object SCHEDULES
    {
        fun getSchedules(serverId: String):RouteModel{return RouteModel("GET", "servers/${serverId}/schedules", "application/json")}
        fun getSchedule(serverId: String, id:Int):RouteModel{return RouteModel("GET", "servers/${serverId}/schedules/${id}", "application/json")}
        fun createSchedule(serverId: String):RouteModel{return RouteModel("POST", "servers/${serverId}/schedules", "application/json")}
        fun updateSchedule(serverId: String, id:Int):RouteModel{return RouteModel("POST", "servers/${serverId}/schedules/${id}", "application/json")}
        fun deleteSchedule(serverId: String, id:Int):RouteModel{return RouteModel("DELETE", "servers/${serverId}/schedules/${id}", "application/json")}
        fun createTask(serverId: String, id:Int):RouteModel{return RouteModel("POST", "servers/${serverId}/schedules/${id}/tasks", "application/json")}
        fun updateTask(serverId: String, scheduleId:Int, taskId:Int):RouteModel{return RouteModel("POST", "servers/${serverId}/schedules/${scheduleId}/tasks/${taskId}", "application/json")}
        fun deleteTask(serverId: String, scheduleId:Int, taskId:Int):RouteModel{return RouteModel("DELETE", "servers/${serverId}/schedules/${scheduleId}/tasks/${taskId}", "application/json")}

    }

}