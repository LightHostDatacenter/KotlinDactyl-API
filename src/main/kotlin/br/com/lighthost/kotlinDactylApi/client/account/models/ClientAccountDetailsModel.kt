package br.com.lighthost.kotlinDactylApi.client.account.models

import br.com.lighthost.kotlinDactylApi.client.account.actions.ClientAccountActions

data class ClientAccountDetailsModel(
    @JvmField val id:Int,
    @JvmField val isAdmin:Boolean,
    @JvmField val username:String,
    @JvmField val email:String,
    @JvmField val firstName:String,
    @JvmField val lastName:String,
    @JvmField val language:String,
    @JvmField val actions:ClientAccountActions)
