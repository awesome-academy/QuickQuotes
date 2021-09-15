package com.sun.quickquotes.di

import com.sun.quickquotes.data.source.remote.utils.APIConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { initHttpClient() }
    single { initRetrofit(get()) }
}

fun initHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    val builder = OkHttpClient.Builder()
    builder.addInterceptor { chain ->
        val original = chain.request()
        val url = original.url.newBuilder().build()
        val request = original.newBuilder().url(url).build()
        chain.proceed(request)
    }
        .addInterceptor(httpLoggingInterceptor)
    return builder.build()
}

fun initRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(APIConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
