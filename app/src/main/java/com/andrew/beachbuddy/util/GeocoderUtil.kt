package com.andrew.beachbuddy.util

import android.content.Context
import android.location.Geocoder
import android.os.Build
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import java.util.Locale

const val DEFAULT_LOCALITY = "Siesta Key"

fun getLocalityFromLatLong(
    context: Context,
    latitude: Double,
    longitude: Double
): Flow<String> = callbackFlow {
    try {
        val geocoder = Geocoder(context, Locale.getDefault())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(latitude, longitude, 1) { addresses ->
                val locality = if (addresses.isNotEmpty()) {
                    addresses[0].locality ?: DEFAULT_LOCALITY
                } else {
                    Timber.w("Locality was not found!")
                    DEFAULT_LOCALITY
                }
                trySend(locality)
                close() // No more values will be sent
            }
        } else {
            close(Throwable("SDK Version not supported"))
        }
    } catch (e: Exception) {
        Timber.e(e)
        close(e)
    }
    awaitClose { /* No-op cleanup */ }
}