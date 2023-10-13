package com.khaleejtimes.test.utils

import java.util.regex.Pattern

object Constants {

    const val UNKNOWN_ERROR = "Unknown Error"
    const val API_KEY = "2a5b2ac49c1f479f91c2c0f8a58d5456"
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
}