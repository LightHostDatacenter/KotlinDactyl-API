package br.com.lighthost.kotlinDactylApi.requests.routes

import br.com.lighthost.kotlinDactylApi.requests.routes.models.RouteModel

class ApplicationRoutes {

    object USERS {
        fun getUsers(): RouteModel {
            return RouteModel("GET", "users", "application/json")
        }

        fun getUser(id:Int): RouteModel {
            return RouteModel("GET", "users/${id}", "application/json")
        }

        fun getUserByExternalId(externalId:String): RouteModel {
            return RouteModel("GET", "users/external/${externalId}", "application/json")
        }

        fun createUser(): RouteModel {
            return RouteModel("POST", "users", "application/json")
        }

        fun updateUser(id:Int): RouteModel {
            return RouteModel("PATCH", "users/${id}", "application/json")
        }

        fun deleteUser(id:Int): RouteModel {
            return RouteModel("DELETE", "users/${id}", "application/json")
        }
    }

    object NODES {
        fun getNodes(): RouteModel {
            return RouteModel("GET", "nodes/?include=servers,allocations", "application/json")
        }

        fun getNode(id:Int): RouteModel {
            return RouteModel("GET", "nodes/${id}/?include=servers,allocations", "application/json")
        }

        fun getNodeConfiguration(id:Int): RouteModel {
            return RouteModel("GET", "nodes/${id}/configuration", "application/json")
        }

        fun createNode(): RouteModel {
            return RouteModel("POST", "nodes", "application/json")
        }

        fun updateNode(id:Int): RouteModel {
            return RouteModel("PATCH", "nodes/${id}", "application/json")
        }

        fun deleteNode(id:Int): RouteModel {
            return RouteModel("DELETE", "nodes/${id}", "application/json")
        }
    }

    object NESTS {
        fun getNests(): RouteModel {
            return RouteModel("GET", "nests", "application/json")
        }

        fun getNest(id:Int): RouteModel {
            return RouteModel("GET", "nests/${id}", "application/json")
        }
    }

    object EGGS {
        fun getEggs(nestId:Int): RouteModel {
            return RouteModel("GET", "nests/${nestId}/eggs/?include=variables", "application/json")
        }

        fun getEgg(nestId:Int ,id:Int): RouteModel {
            return RouteModel("GET", "nests/${nestId}/eggs/${id}/?include=variables", "application/json")
        }
    }

    object ALLOCATIONS {
        fun getAllocations(nodeId:Int): RouteModel {
            return RouteModel("GET", "nodes/${nodeId}/allocations", "application/json")
        }

        fun createAllocation(nodeId: Int): RouteModel {
            return RouteModel("POST", "nodes/${nodeId}/allocations/", "application/json")
        }

        fun deleteAllocation(nodeId: Int, allocationId:Int): RouteModel {
            return RouteModel("DELETE", "nodes/${nodeId}/allocations/${allocationId}", "application/json")
        }
    }

    object LOCATIONS {
        fun getLocations(): RouteModel {
            return RouteModel("GET", "locations", "application/json")
        }

        fun createLocation(): RouteModel {
            return RouteModel("POST", "locations", "application/json")
        }

        fun getLocation(id:Int): RouteModel {
            return RouteModel("GET", "locations/${id}", "application/json")
        }

        fun updateLocation(id:Int): RouteModel {
            return RouteModel("PATCH", "locations/${id}", "application/json")
        }

        fun deleteLocation(id:Int): RouteModel {
            return RouteModel("DELETE", "locations/${id}", "application/json")
        }
    }

}