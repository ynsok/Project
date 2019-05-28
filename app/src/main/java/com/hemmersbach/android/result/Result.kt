package com.hemmersbach.android.result

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val string: String) : Result<Nothing>()
    data class Exception(val exception: kotlin.Exception) : Result<Nothing>()
    data class Loading(val string: String = "") : Result<Nothing>()
}
