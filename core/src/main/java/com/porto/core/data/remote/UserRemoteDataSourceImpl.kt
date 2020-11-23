package com.porto.core.data.remote

import com.porto.commons.models.Result
import com.porto.core.data.user.source.UserRemoteDataSource
import com.porto.core.domain.user.models.User
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor() : UserRemoteDataSource {

    override suspend fun getUserRemote(): Result<User> {
        return Result.Success(Dummy().createUser())
    }

}