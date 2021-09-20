package com.sun.quickquotes.di

import com.sun.quickquotes.ui.getstarted.GetStartedViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { GetStartedViewModel(get()) }
}
