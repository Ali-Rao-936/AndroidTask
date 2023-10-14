package com.khaleejtimes.test.utils

import android.text.format.DateUtils
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Utils {

    fun convertDate(date : String) : String {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        var d: Date? = null
        try {
            d = input.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val niceDateStr: String = d?.let {
            DateUtils.getRelativeTimeSpanString(
                it.time,
                Calendar.getInstance().timeInMillis,
                DateUtils.MINUTE_IN_MILLIS
            )
        }.toString()
        Log.d("DATE", "" + niceDateStr)
        return niceDateStr
    }
}