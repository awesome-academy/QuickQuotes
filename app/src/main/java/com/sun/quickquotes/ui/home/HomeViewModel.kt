package com.sun.quickquotes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.quickquotes.base.BaseViewModel
import com.sun.quickquotes.data.model.Quote
import com.sun.quickquotes.data.repository.QuoteRepository

class HomeViewModel(
    private val quoteRepository: QuoteRepository
) : BaseViewModel() {

    private val _quotes = MutableLiveData<MutableList<Quote>>()
    val quotes: LiveData<MutableList<Quote>>
        get() = _quotes

    companion object {
        private const val DEFAULT_TYPE_PARAMS = "tag"
        private const val DEFAULT_VALUE_PARAMS = "motivational"
    }
}
