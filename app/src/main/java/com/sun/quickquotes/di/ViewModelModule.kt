package com.sun.quickquotes.di

import com.sun.quickquotes.ui.category.CategoryViewModel
import com.sun.quickquotes.ui.getstarted.GetStartedViewModel
import com.sun.quickquotes.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GetStartedViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
}
