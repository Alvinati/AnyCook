package com.porto.commons.base

import com.porto.commons.models.Result

abstract class UseCase<SuccessType : Any, in Params> {

    abstract suspend fun build(params: Params?): Result<SuccessType>

    suspend fun execute(params: Params? = null): Result<SuccessType> {
        return try {
            build(params)
        } catch (error: Throwable) {
            Result.Error(error.message)
        }
    }
}