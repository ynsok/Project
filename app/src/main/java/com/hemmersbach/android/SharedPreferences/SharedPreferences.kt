package com.hemmersbach.android.SharedPreferences

import android.content.Context
import android.preference.PreferenceManager
import com.hemmersbach.android.R

class SharedPreferences(private val context: Context) {

    private fun getSharedPreferences() = PreferenceManager.getDefaultSharedPreferences(context)

    fun getItemCountValue() = getSharedPreferences().getString(
        context.getString(R.string.numberOfJokesKey),
        DEF_VALUE
    )!!.toInt()

    companion object {
        const val DEF_VALUE = "3"
    }
}
