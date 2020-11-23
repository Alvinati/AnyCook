package com.porto.core.domain.authentication

import com.porto.commons.base.UseCase
import com.porto.commons.models.Result
import com.porto.core.domain.authentication.repository.AuthenticationRepository
import com.porto.core.domain.user.models.InitialPage
import com.porto.core.domain.user.repository.UserRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository) : UseCase<InitialPage, Unit>() {

    override suspend fun build(params: Unit?): Result<InitialPage> {
        authenticationRepository.setToken("")
        userRepository.clearUser()
        return Result.Success(InitialPage.Authentication)
    }
}