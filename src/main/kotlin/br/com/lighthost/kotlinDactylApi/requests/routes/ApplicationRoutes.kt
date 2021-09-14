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