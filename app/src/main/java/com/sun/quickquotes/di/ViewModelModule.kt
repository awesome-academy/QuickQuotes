package com.sun.quickquotes.di

import com.sun.quickquotes.ui.getstarted.GetStartedViewModel
import com.sun.quickquotes.ui.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { GetStartedViewModel(get()) }
    single { HomeViewModel(get()) }
}
