package kr.ac.tukorea.whereareu.util

import android.util.Log
import kr.ac.tukorea.whereareu.util.AppConfig
import okhttp3.Interceptor
import okhttp3.Response

class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val originRequest = chain.request()
            Log.d(AppConfig.TAG_DEBUG, "HttpRequestInterceptor: ${originRequest.url}")

            return chain.proceed(originRequest)
        } catch (e: Exception) {
            Log.d(AppConfig.TAG_DEBUG, "HttpRequestInterceptor error: ${e.message}")
            throw e
        }
    }
}