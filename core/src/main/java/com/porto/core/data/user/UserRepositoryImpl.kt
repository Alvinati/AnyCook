package com.porto.core.data.user

import com.porto.commons.models.Result
import com.porto.core.data.user.entity.UserEntity
import com.porto.core.data.user.source.UserLocalDataSource
import com.porto.core.data.user.source.UserRemoteDataSource
import com.porto.core.domain.user.models.User
import com.porto.core.domain.user.repository.UserRepository

class UserRepositoryImpl (private val remoteDataSource: UserRemoteDataSource,
        private val localDataSource: UserLocalDataSource) : UserRepository{

        override suspend fun getRemoteUser(): Result<User> {
               return when(val result = remoteDataSource.getUserRemote()){
                        is Result.Success -> {
                                Result.Success(result.value)
                        }
                        is Result.Error -> Result.Error(result.errorMessage
                                , result.statusCode, result.httpCode)
                }
        }

        override fun getCurrentUser(): User {
               return localDataSource.getCurrentUser().toUser()
        }

        override fun setCurrentUser(user: User) {
                localDataSource.setCurrentUser(UserEntity.create(user))
        }

        override fun clearUser() {
                localDataSource.clearUserData()
        }

}