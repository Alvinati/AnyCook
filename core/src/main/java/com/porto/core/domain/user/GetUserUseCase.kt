package com.porto.core.domain.user

import com.porto.commons.base.UseCase
import com.porto.commons.models.Result
import com.porto.core.domain.authentication.repository.AuthenticationRepository
import com.porto.core.domain.user.models.User
import com.porto.core.domain.user.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
        private val userRepository: UserRepository,
        private val authRepository : AuthenticationRepository
)
    : UseCase<User, Unit>() {

    override suspend fun build(params: Unit?): Result<User> {


        if(!authRepository.isAuthorized())
            return Result.Error("Sorry, You're not authenticated")

        val cachedData = userRepository.getCurrentUser()

        return     if(cachedData.name.isNotEmpty())
            Result.Success(cachedData)
        else {
            return when(val res = userRepository.getRemoteUser()){
                is Result.Success -> res
                is Result.Error -> res.apply {
                    authRepository.setToken("")
                    userRepository.clearUser()
                }
            }
        }
    }

}
