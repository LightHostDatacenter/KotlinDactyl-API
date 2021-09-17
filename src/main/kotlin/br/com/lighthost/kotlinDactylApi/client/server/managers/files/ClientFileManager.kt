package br.com.lighthost.kotlinDactylApi.client.server.managers.files

import br.com.lighthost.kotlinDactylApi.client.server.managers.details.ClientServerDetails
import br.com.lighthost.kotlinDactylApi.client.server.managers.files.models.ClientFileModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ClientRoutes
import org.json.JSONArray
import org.json.JSONObject
import java.time.OffsetDateTime

class ClientFileManager (private val server: ClientServerDetails, private val baseRequest: BaseRequest) {

    fun retrieveFiles(directory:String): MutableList<ClientFileModel> {
        val filesList : MutableList<ClientFileModel> = mutableListOf()
        val filesArray = JSONObject(baseRequest.executeRequest(ClientRoutes.FILES.listFiles(server.attributes.identifier, directory), null)).getJSONArray("data")
        for (i:Int in 0 until filesArray.length()){
            val it = filesArray.getJSONObject(i)
            filesList.add(clientFileManagerParser(it.getJSONObject("attributes")))
        }
        return filesList
    }

    fun retrieveFileContent(filePath:String): String {
        return baseRequest.executeRequest(ClientRoutes.FILES.getContents(server.attributes.identifier, filePath), null)
    }

    fun retrieveFileDownloadUrl(filePath: String) : String{
        return JSONObject(baseRequest.executeRequest(ClientRoutes.FILES.downloadFile(server.attributes.identifier, filePath), null)).getJSONObject("attributes").getString("url")
    }

    fun renameFile(rootDir:String, from:String, to:String){
        val fromTo = JSONObject().put("from", from).put("to", to)
        val json = JSONObject().accumulate("root", rootDir).append("files", fromTo)
        baseRequest.executeRequest(ClientRoutes.FILES.renameFile(server.attributes.identifier), json.toString())
    }

    fun duplicateFile(filePath: String){
        baseRequest.executeRequest(ClientRoutes.FILES.copyFile(server.attributes.identifier),JSONObject().accumulate("location", filePath).toString())
    }

    fun writeFile(filePath: String, content:String){
        baseRequest.executeRequest(ClientRoutes.FILES.writeFile(server.attributes.identifier, filePath), content)
    }

    fun compressFiles(rootDir: String, files: HashSet<String>): ClientFileModel {
        val json = JSONObject().accumulate("root", rootDir).accumulate("files", files)
        return clientFileManagerParser(
            JSONObject(
                baseRequest.executeRequest(ClientRoutes.FILES.compressFiles(server.attributes.identifier), json.toString())
            ).getJSONObject("attributes"))
    }

    fun decompressFile(rootDir: String, filePath: String){
        val json = JSONObject().accumulate("root", rootDir).accumulate("file", filePath)
        baseRequest.executeRequest(ClientRoutes.FILES.decompressFile(server.attributes.identifier), json.toString())
    }

    fun deleteFiles(rootDir: String, files: HashSet<String>) {
        val json = JSONObject().accumulate("root", rootDir).accumulate("files", files)
        baseRequest.executeRequest(ClientRoutes.FILES.deleteFiles(server.attributes.identifier), json.toString())
    }

    fun createFolder(rootDir: String, folderName:String){
        val json = JSONObject().accumulate("root", rootDir).accumulate("name", folderName)
        baseRequest.executeRequest(ClientRoutes.FILES.createFolder(server.attributes.identifier), json.toString())
    }

    fun retrieveUploadUrl(): String {
        return JSONObject(baseRequest.executeRequest(ClientRoutes.FILES.uploadFile(server.attributes.identifier), null))
            .getJSONObject("attributes").getString("url")
    }

    fun uploadFileByUrl(rootDir: String, url:String){
        baseRequest.executeRequest(ClientRoutes.FILES.uploadFileByUrl(server.attributes.identifier, rootDir, url), "")
    }

    fun chmodFile(rootDir: String, file: String, mode:String){
        val fileList = JSONArray()
        fileList.put(JSONObject().put("file", file).accumulate("mode", mode))
        val json = JSONObject().accumulate("root", rootDir).put("files", fileList)
        baseRequest.executeRequest(ClientRoutes.FILES.chmodFile(server.attributes.identifier), json.toString())
    }

        private fun clientFileManagerParser(json : JSONObject):ClientFileModel{
            return ClientFileModel(
                json.getString("name"),
                json.getString("mode"),
                json.getString("mode_bits"),
                json.getLong("size"),
                json.getBoolean("is_file"),
                json.getBoolean("is_symlink"),
                json.getString("mimetype"),
                OffsetDateTime.parse(json.getString("created_at")),
                OffsetDateTime.parse(json.getString("modified_at")))
        }

}