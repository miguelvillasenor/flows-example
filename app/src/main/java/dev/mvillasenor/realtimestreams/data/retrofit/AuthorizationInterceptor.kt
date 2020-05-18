package dev.mvillasenor.realtimestreams.data.retrofit

import dev.mvillasenor.realtimestreams.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.APP_TOKEN}")
            .header("Client-ID", BuildConfig.CLIENT_ID)
            .build()
        return chain.proceed(request)
    }
}
