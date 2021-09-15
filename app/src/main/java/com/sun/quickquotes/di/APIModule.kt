package com.sun.quickquotes.di

import com.sun.quickquotes.data.source.remote.utils.APIService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(APIService::class.java) }
}
