package com.porto.core.data.user.source

import com.porto.commons.models.Result
import com.porto.core.domain.user.models.User

interface UserRemoteDataSource {
    suspend fun getUserRemote() : Result<User>
}