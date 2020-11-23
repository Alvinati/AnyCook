package com.porto.core.data.remote

import com.porto.commons.models.Result
import com.porto.core.data.authentication.source.AuthenticationRemoteDataSource
import javax.inject.Inject

class AuthenticationRemoteDataSourceImpl @Inject constructor()
    : AuthenticationRemoteDataSource {

    override suspend fun login(mail: String?, password: String?): Result<String> {

        if(mail.isNullOrEmpty() || password.isNullOrEmpty())
            return Result.Error("Field email / password tidak boleh kosong")

        return Result.Success(Dummy().userToken)
    }

    override suspend fun logout() : Result<Unit>{
        return Result.Success(Unit)
    }
}