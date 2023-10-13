package com.khaleejtimes.test.data.common.utils


import android.content.Context
import com.khaleejtimes.test.R
import java.io.IOException

class NoConnectionException(private val context:Context):IOException() {
    override val message: String
        get() =context.getString(R.string.no_internet_connection_message)
}

class NoInternetException(private val context:Context) : IOException() {
    override val message: String
        get() = context.getString(R.string.no_internet_active_message)
}