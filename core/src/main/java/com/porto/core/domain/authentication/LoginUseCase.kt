package com.porto.core.domain.authentication

import com.porto.commons.base.UseCase
import com.porto.commons.models.Result
import com.porto.core.domain.authentication.repository.AuthenticationRepository
import com.porto.core.domain.user.GetUserUseCase
import com.porto.core.domain.user.models.InitialPage
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val getUserUseCase: GetUserUseCase
)
    : UseCase<InitialPage, LoginUseCase.Params>() {

    override suspend fun build(params: Params?) : Result<InitialPage>{
        return when(val result = authenticationRepository.login(params?.email, params?.password)){
            is Result.Success -> {
                authenticationRepository.setToken(result.value)
                return when(val res = getUserUseCase.execute()) {
                    is Result.Success -> {
                        Result.Success(InitialPage.Home)
                    }
                    is Result.Error -> {
                        Result.Error(res.errorMessage, res.statusCode)
                    }
                }

            }
            is Result.Error -> Result.Error(result.errorMessage, result.statusCode)
        }
    }

    class Params(val email : String, val password: String)
}
