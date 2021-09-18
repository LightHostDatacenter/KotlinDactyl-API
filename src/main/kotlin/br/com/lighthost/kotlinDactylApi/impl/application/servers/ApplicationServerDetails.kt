package br.com.lighthost.kotlinDactylApi.impl.application.servers

import br.com.lighthost.kotlinDactylApi.impl.application.servers.actions.ApplicationServerActions
import br.com.lighthost.kotlinDactylApi.impl.application.servers.models.*
import br.com.lighthost.kotlinDactylApi.impl.common.models.servers.ServerFeatureLimitsModel
import br.com.lighthost.kotlinDactylApi.impl.common.models.servers.ServerLimitsModel
import br.com.lighthost.kotlinDactylApi.requests.BaseRequest
import br.com.lighthost.kotlinDactylApi.requests.routes.ApplicationRoutes
import org.json.JSONObject
import java.time.OffsetDateTime

class ApplicationServerDetails(json: JSONObject, private val baseRequest: BaseRequest) {

    private val jsonLimits = json.getJSONObject("limits")
    private val jsonFeatureLimits = json.getJSONObject("feature_limits")
    private val jsonContainer = json.getJSONObject("container")
    private val jsonAllocations = json.optJSONObject("relationships")?.getJSONObject("allocations")?.getJSONArray("data")
    private val jsonSubUsers = json.optJSONObject("relationships")?.getJSONObject("subusers")?.getJSONArray("data")
    private val jsonVariables = json.optJSONObject("relationships")?.getJSONObject("variables")?.getJSONArray("data")
    private val jsonDatabases = json.optJSONObject("relationships")?.getJSONObject("databases")?.getJSONArray("data")

    private val limits = ServerLimitsModel(
        jsonLimits.getInt("memory"),
        jsonLimits.getInt("swap"),
        jsonLimits.getInt("disk"),
        jsonLimits.getInt("io"),
        jsonLimits.getInt("cpu")
    )

    private val featureLimits = ServerFeatureLimitsModel(
        jsonFeatureLimits.getInt("databases"),
        jsonFeatureLimits.getInt("allocations"),
        jsonFeatureLimits.getInt("backups")
    )

    val startup = ApplicationServerStartupModel(
        jsonContainer.getString("startup_command"),
        parseEggEnvValues(),
        json.getInt("egg"),
        jsonContainer.getString("image"),
        false,
        baseRequest,
        this
    )

    val build = ApplicationServerBuildModel(
        json.getInt("allocation"),
        limits,
        featureLimits,
        baseRequest,
        this)

    val details = ApplicationServerDetailsModel(
        json.getInt("id"),
        json.getString("name"),
        json.optString("description").ifBlank { null },
        json.getInt("user"),
        json.optString("external_id").ifBlank { null },
        json.getString("uuid"),
        json.getBoolean("suspended"),
        json.optString("status").ifBlank { null },
        json.getInt("nest"),
        jsonContainer.getInt("installed"),
        baseRequest,
        this)

    val createdAt: OffsetDateTime? = OffsetDateTime.parse(json.getString("created_at"))
    val updatedAt: OffsetDateTime? = OffsetDateTime.parse(json.getString("updated_at"))
    val allocations = parseAllocations()
    val subUsers = parseSubUsers()
    val databases = parseDatabases()

    fun createDatabase(name: String, remote: String, host: Int): Int {
        val json = JSONObject()
            .accumulate("database", name)
            .accumulate("remote", remote)
            .accumulate("host", host)
        return JSONObject(
            baseRequest.executeRequest(ApplicationRoutes.DATABASES.createDatabase(details.id),
                json.toString())).getJSONObject("attributes").getInt("id")
    }

    fun actions(): ApplicationServerActions {
        return ApplicationServerActions(baseRequest, this)
    }

    private fun parseSubUsers(): List<ApplicationServerSubUserModel>? {
        if (jsonSubUsers == null) { return null }
        val subUsersList: MutableList<ApplicationServerSubUserModel> = mutableListOf()
        for (i: Int in 0 until jsonSubUsers.length()) {
            val it = JSONObject(jsonSubUsers[i].toString()).getJSONObject("attributes")
            val permissionsList: MutableList<String> = mutableListOf()
            val permissionsArray =
                JSONObject(jsonSubUsers[i].toString()).getJSONObject("attributes").getJSONArray("permissions")
            for (arrayI: Int in 0 until permissionsArray.length()) {
                permissionsList.add(permissionsArray[arrayI].toString())
            }
            subUsersList.add(
                ApplicationServerSubUserModel(
                    it.getInt("id"),
                    it.getInt("user_id"),
                    it.getInt("server_id"),
                    permissionsList,
                    OffsetDateTime.parse(it.getString("created_at")),
                    OffsetDateTime.parse(it.getString("updated_at"))
                )
            )
        }
        return subUsersList
    }

    private fun parseDatabases(): List<ApplicationServerDatabaseModel>? {
        if (jsonDatabases == null) { return null }
        val databasesList: MutableList<ApplicationServerDatabaseModel> = mutableListOf()
        for (i: Int in 0 until jsonDatabases.length()) {
            val it = JSONObject(jsonDatabases[i].toString()).getJSONObject("attributes")
            databasesList.add(
                ApplicationServerDatabaseModel(
                    it.getInt("id"),
                    it.getInt("server"),
                    it.getInt("host"),
                    it.getString("database"),
                    it.getString("username"),
                    it.getString("remote"),
                    it.getInt("max_connections"),
                    OffsetDateTime.parse(it.getString("created_at")),
                    OffsetDateTime.parse(it.getString("updated_at")),
                    baseRequest,
                    this
                )
            )
        }
        return databasesList
    }

    private fun parseAllocations(): List<ApplicationServerAllocationModel>? {
        if (jsonAllocations == null) { return null }
        val allocationsList: MutableList<ApplicationServerAllocationModel> = mutableListOf()
        for (i: Int in 0 until jsonAllocations.length()) {
            val it = JSONObject(jsonAllocations[i].toString()).getJSONObject("attributes")
            allocationsList.add(
                ApplicationServerAllocationModel(
                    it.getInt("id"),
                    it.getString("ip"),
                    it.getString("alias"),
                    it.getLong("port"),
                    it.optString("notes").ifBlank { null },
                    it.getBoolean("assigned")
                )
            )
        }
        return allocationsList
    }

    private fun parseEggEnvValues(): Map<String, String>? {
        if(jsonVariables == null) {return null}
        val envList: MutableMap<String, String> = mutableMapOf()
        for (i: Int in 0 until jsonVariables.length()) {
            val env = JSONObject(jsonVariables[i].toString()).getJSONObject("attributes").getString("env_variable")
            val envValue = JSONObject(jsonVariables[i].toString()).getJSONObject("attributes").getString("server_value")
            envList[env] = envValue
        }
        return envList
    }

}