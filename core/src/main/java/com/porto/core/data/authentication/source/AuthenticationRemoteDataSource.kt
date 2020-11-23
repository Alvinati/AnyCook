package com.porto.core.data.authentication.source

import com.porto.commons.models.Result

interface AuthenticationRemoteDataSource {
    suspend fun login(mail:String?, password: String?) : Result<String>
    suspend fun logout() : Result<Unit>
}