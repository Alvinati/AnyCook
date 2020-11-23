package com.porto.core.domain.authentication

import com.porto.commons.base.UseCase
import com.porto.commons.models.Result
import com.porto.core.domain.authentication.repository.AuthenticationRepository
import com.porto.core.domain.user.models.InitialPage
import javax.inject.Inject

class GetAuthenticationUseCase @Inject constructor(private val repository: AuthenticationRepository)
    : UseCase<InitialPage, Unit>() {

    override suspend fun build(params: Unit?): Result<InitialPage> {
        val res = repository.isAuthorized()

        return if(res)
            Result.Success(InitialPage.Home)
        else Result.Success(InitialPage.Authentication)
    }

}