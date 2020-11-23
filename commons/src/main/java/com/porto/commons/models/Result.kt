package com.porto.commons.models


@Suppress("unused")
sealed class Result<out T: Any> {
    data class Success<T : Any>(val value: T, val next: String = "") : Result<T>()
    data class Error(val errorMessage: String?, val statusCode: Int = 0, val httpCode: Int = 0)
        : Result<Nothing>()
}