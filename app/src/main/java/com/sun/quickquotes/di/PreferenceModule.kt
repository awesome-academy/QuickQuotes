package com.sun.quickquotes.di

import android.content.Context
import android.content.SharedPreferences
import com.sun.quickquotes.utils.SharedPrefsKey.KEY_PREFERENCE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferenceModule = module {
    single { customPrefs(androidContext()) }
}

private fun customPrefs(context: Context): SharedPreferences =
    context.getSharedPreferences(KEY_PREFERENCE_NAME, Context.MODE_PRIVATE)
