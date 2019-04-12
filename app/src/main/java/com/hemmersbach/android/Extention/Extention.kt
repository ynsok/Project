package com.hemmersbach.android.Extention

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hemmersbach.android.Result.Result
import java.io.IOException
import java.lang.Exception

suspend fun <T : Any> ApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> =
    try {
        call.invoke()
    } catch (e: Exception) {
        Result.Failure(IOException(errorMessage, e))
    }

fun ViewGroup.inflate(): LayoutInflater = LayoutInflater.from(context)
