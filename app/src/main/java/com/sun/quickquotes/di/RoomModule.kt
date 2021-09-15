package com.sun.quickquotes.di

import android.content.Context
import androidx.room.Room
import com.sun.quickquotes.data.source.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single { initAppDatabase(androidContext()) }
    single { get<AppDatabase>().authorDao() }
    single { get<AppDatabase>().collectionDao() }
    single { get<AppDatabase>().quoteDao() }
    single { get<AppDatabase>().tagDao() }
}

private fun initAppDatabase(context: Context) =
    Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).build()
