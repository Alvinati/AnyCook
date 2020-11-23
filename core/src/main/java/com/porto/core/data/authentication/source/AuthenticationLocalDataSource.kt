package com.porto.core.data.authentication.source

interface AuthenticationLocalDataSource {
     fun setToken(token: String)
     fun getToken() : String
     fun isAuthorized() : Boolean
}