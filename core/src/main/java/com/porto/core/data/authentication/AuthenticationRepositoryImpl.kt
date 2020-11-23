package com.porto.core.data.authentication

import com.porto.commons.models.Result
import com.porto.core.data.authentication.source.AuthenticationLocalDataSource
import com.porto.core.data.authentication.source.AuthenticationRemoteDataSource
import com.porto.core.domain.authentication.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthenticationRemoteDataSource,
    private val localDataSource: AuthenticationLocalDataSource)
    : AuthenticationRepository {

    override suspend fun login(email: String?, password: String?): Result<String> {
        return remoteDataSource.login(email, password)
    }

    override suspend fun logout() : Result<Unit> {
        return remoteDataSource.logout()
    }

    override fun setToken(token: String) {
       localDataSource.setToken(token)
    }

    override fun getToken(): String {
        return localDataSource.getToken()
    }

    override fun isAuthorized(): Boolean {
        return localDataSource.isAuthorized()
    }
}