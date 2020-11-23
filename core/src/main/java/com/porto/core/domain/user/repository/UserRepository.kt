package com.porto.core.domain.user.repository

import com.porto.commons.models.Result
import com.porto.core.domain.user.models.User


interface UserRepository {

    suspend fun getRemoteUser() : Result<User>
    fun getCurrentUser() : User
    fun setCurrentUser(user : User)
    fun clearUser()
}