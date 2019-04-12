package com.hemmersbach.android.Util

import android.content.Context
import android.net.ConnectivityManager

class ConnectionManager(private val context: Context) {

    fun checkIfIsInternet(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}