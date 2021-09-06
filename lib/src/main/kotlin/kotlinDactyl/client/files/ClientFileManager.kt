package kotlinDactyl.client.files

import kotlinDactyl.client.details.ClientServerDetails
import kotlinDactyl.client.files.models.ClientFileModel
import kotlinDactyl.requests.BaseRequest
import kotlinDactyl.requests.RouteModels.ClientRoutes
import org.json.JSONArray
import org.json.JSONObject
import java.time.OffsetDateTime

class ClientFileManager (private val server: ClientServerDetails, private val baseRequest: BaseRequest) {

    fun getFilesList(directory:String): MutableList<ClientFileModel> {
        val filesList : MutableList<ClientFileModel> = mutableListOf()
        JSONObject(baseRequest.executeRequest(ClientRoutes.FILES.listFiles(server.identifier, directory), null)).getJSONArray("data").forEach{
            it as JSONObject; filesList.add(ClientFileManagerParser.parse(it.getJSONObject("attributes").toString()))
        }; return filesList
    }

    fun getFileContent(filePath:String): String {
        return baseRequest.executeRequest(ClientRoutes.FILES.getContents(server.identifier, filePath), null)
    }

    fun getFileDownloadLink(filePath: String) : String{
        return JSONObject(baseRequest.executeRequest(ClientRoutes.FILES.downloadFile(server.identifier, filePath), null)).getJSONObject("attributes").getString("url")
    }

    fun renameFile(rootDir:String, from:String, to:String){
        val fromTo = JSONObject().put("from", from).put("to", to)
        val json = JSONObject().accumulate("root", rootDir).append("files", fromTo)
        baseRequest.executeRequest(ClientRoutes.FILES.renameFile(server.identifier), json.toString())
    }

    fun duplicateFile(filePath: String){
        baseRequest.executeRequest(ClientRoutes.FILES.copyFile(server.identifier),JSONObject().accumulate("location", filePath).toString())
    }

    fun writeFile(filePath: String, content:String){
        baseRequest.executeRequest(ClientRoutes.FILES.writeFile(server.identifier, filePath), content)
    }

    fun compressFiles(rootDir: String, files: HashSet<String>): ClientFileModel {
        val json = JSONObject().accumulate("root", rootDir).accumulate("files", files)
        return ClientFileManagerParser.parse(
            JSONObject(
                baseRequest.executeRequest(ClientRoutes.FILES.compressFiles(server.identifier), json.toString())
            ).getJSONObject("attributes").toString())
    }

    fun decompressFile(rootDir: String, filePath: String){
        val json = JSONObject().accumulate("root", rootDir).accumulate("file", filePath)
        baseRequest.executeRequest(ClientRoutes.FILES.decompressFile(server.identifier), json.toString())
    }

    fun deleteFiles(rootDir: String, files: HashSet<String>) {
        val json = JSONObject().accumulate("root", rootDir).accumulate("files", files)
        baseRequest.executeRequest(ClientRoutes.FILES.deleteFiles(server.identifier), json.toString())
    }

    fun createFolder(rootDir: String, folderName:String){
        val json = JSONObject().accumulate("root", rootDir).accumulate("name", folderName)
        baseRequest.executeRequest(ClientRoutes.FILES.createFolder(server.identifier), json.toString())
    }

    fun getUploadUrl(): String {
        return JSONObject(baseRequest.executeRequest(ClientRoutes.FILES.uploadFile(server.identifier), null))
            .getJSONObject("attributes").getString("url")
    }

    fun uploadFileByUrl(rootDir: String, url:String){
        baseRequest.executeRequest(ClientRoutes.FILES.uploadFileByUrl(server.identifier, rootDir, url), "")
    }

    fun chmodFile(rootDir: String, file: String, mode:String){
        val fileList = JSONArray()
        fileList.put(JSONObject().put("file", file).accumulate("mode", mode))
        val json = JSONObject().accumulate("root", rootDir).put("files", fileList)
        baseRequest.executeRequest(ClientRoutes.FILES.chmodFile(server.identifier), json.toString())
    }

    object ClientFileManagerParser {
        fun parse(rawJson : String):ClientFileModel{
            val json = JSONObject(rawJson)
            return ClientFileModel(
                json.getString("name"),
                json.getString("mode"),
                json.getString("mode_bits"),
                json.getInt("size"),
                json.getBoolean("is_file"),
                json.getBoolean("is_symlink"),
                json.getString("mimetype"),
                OffsetDateTime.parse(json.getString("created_at")),
                OffsetDateTime.parse(json.getString("modified_at")))
        }
    }

}