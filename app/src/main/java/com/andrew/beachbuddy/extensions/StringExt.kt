package com.andrew.beachbuddy.extensions

import java.time.ZonedDateTime

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it.lowercase().capitalize() }

fun String?.convertServerTimeToMillis(): Long =
    if (this == null) {
        0
    } else {
        ZonedDateTime.parse(this)
            .toInstant()
            .toEpochMilli()
    }
