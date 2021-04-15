package com.ahsailabs.beritakita_kotlin.utils

import android.content.Context
import android.util.Base64
import com.ahsailabs.beritakita_kotlin.configs.Config
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by ahmad s on 2019-09-26.
 */
object HttpUtil {
    private const val DATA_DEFAULT_CONNECT_TIMEOUT_MILLIS = 15 * 1000 // 15s
    private const val DATA_DEFAULT_READ_TIMEOUT_MILLIS = 30 * 1000 // 30s
    private const val DATA_DEFAULT_WRITE_TIMEOUT_MILLIS = 30 * 1000 // 30s
    fun getCLient(context: Context): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val builder = chain.request().newBuilder()
            val apiKey = "APIKEY=" + Config.API_KEY
            val encodedApiKey = Base64.encodeToString(apiKey.toByteArray(), Base64.NO_WRAP)
            builder.addHeader("Authorization", encodedApiKey)
            builder.addHeader("x-packagename", context.packageName)
            builder.addHeader("x-platform", "android")
            val sharedPreferences =
                context.getSharedPreferences(Config.APP_PREFERENCES, Context.MODE_PRIVATE)
            val token = sharedPreferences.getString(Config.DATA_TOKEN, "")
            builder.addHeader("x-token", token)
            val newRequest = builder.build()
            chain.proceed(newRequest)
        }
        return OkHttpClient.Builder()
            .connectTimeout(DATA_DEFAULT_CONNECT_TIMEOUT_MILLIS.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(DATA_DEFAULT_READ_TIMEOUT_MILLIS.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(DATA_DEFAULT_WRITE_TIMEOUT_MILLIS.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .build()
    }
}