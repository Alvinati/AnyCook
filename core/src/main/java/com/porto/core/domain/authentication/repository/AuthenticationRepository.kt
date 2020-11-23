package com.porto.core.domain.authentication.repository

import com.porto.commons.models.Result

interface AuthenticationRepository {

     suspend fun login(email: String?, password: String?) : Result<String>
     suspend fun logout() : Result<Unit>
     fun setToken(token : String)
     fun getToken() : String
     fun isAuthorized() : Boolean
}