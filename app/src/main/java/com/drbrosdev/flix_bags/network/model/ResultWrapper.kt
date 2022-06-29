package com.drbrosdev.flix_bags.network.model

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: String? = null): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}


suspend fun <T> executeWithResult(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall())
        } catch (throwable: Throwable) {
            when(throwable) {
                is IOException -> {
                    ResultWrapper.NetworkError
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorMessage = throwable.message()
                    ResultWrapper.GenericError(code, errorMessage)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}