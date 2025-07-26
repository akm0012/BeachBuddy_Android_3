package com.andrew.beachbuddy.util


import java.time.*
import kotlin.math.*

fun calculateSolarNoon(
    date: LocalDate,
    latitude: Double, // not used for solar noon but useful for future expansion
    longitude: Double,
    zoneId: ZoneId
): LocalTime {
    val dayOfYear = date.dayOfYear

    // Step 1: Calculate the equation of time (EoT) in minutes
    val b = 2 * PI * (dayOfYear - 81) / 364
    val equationOfTime = 9.87 * sin(2 * b) - 7.53 * cos(b) - 1.5 * sin(b) // in minutes

    // Step 2: Time offset from UTC based on longitude
    val timeZoneOffsetMinutes = zoneId.rules.getOffset(date.atStartOfDay()).totalSeconds / 60.0
    val lngStdMeridian = timeZoneOffsetMinutes / 4.0 // degrees
    val lngCorrection = 4 * (longitude - lngStdMeridian) // in minutes

    // Step 3: Solar noon in minutes from midnight local time
    val solarNoonMinutes = 720 - lngCorrection - equationOfTime

    return LocalTime.MIN.plusMinutes(solarNoonMinutes.roundToLong())
}

private fun example() {
    val date = LocalDate.now()
    val latitude = 34.07   // Alpharetta, GA
    val longitude = -84.27
    val zoneId = ZoneId.of("America/New_York")

    val solarNoon = calculateSolarNoon(date, latitude, longitude, zoneId)
    println("Solar noon: $solarNoon")

    // To Millis
    val localDateTime = LocalDateTime.of(date, solarNoon)
    val zonedDateTime = localDateTime.atZone(zoneId)
    val epochMillis = zonedDateTime.toInstant().toEpochMilli()

    println("Solar noon in epoch millis (UTC): $epochMillis")
}