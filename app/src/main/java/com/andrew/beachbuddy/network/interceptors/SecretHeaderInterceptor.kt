package com.andrew.beachbuddy.network.interceptors

import com.andrew.beachbuddy.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class SecretHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("AppToken", BuildConfig.APP_SECRET_HEADER)
            .build()

        return chain.proceed(request)
    }

}