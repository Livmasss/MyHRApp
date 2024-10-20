package com.example.core.data.remote

import com.example.core.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .connectTimeout(10000L, TimeUnit.MILLISECONDS)
        .readTimeout(10000L, TimeUnit.MILLISECONDS)
        .writeTimeout(10000L, TimeUnit.MILLISECONDS)
        .build()

    private val gson = GsonBuilder().apply {
        setDateFormat("yyyy-MM-dd")
    }.create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    fun <T> createApi(api: Class<T>): T =
        retrofit.create(api)
}