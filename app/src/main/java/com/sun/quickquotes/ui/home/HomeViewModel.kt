package com.sun.quickquotes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.quickquotes.base.BaseViewModel
import com.sun.quickquotes.data.model.Quote
import com.sun.quickquotes.data.repository.QuoteRepository
import com.sun.quickquotes.data.source.remote.utils.APIConfig
import com.sun.quickquotes.utils.BaseConstant
import com.sun.quickquotes.utils.BaseConstant.DEFAULT_TYPE_PARAMS
import com.sun.quickquotes.utils.BaseConstant.DEFAULT_VALUE_PARAMS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val quoteRepository: QuoteRepository
) : BaseViewModel() {

    private val _quotes = MutableLiveData<MutableList<Quote>>()
    val quotes: LiveData<MutableList<Quote>>
        get() = _quotes

    private suspend fun getAllRandomData() {
        val result = quoteRepository.getRandomQuotes()
        _quotes.postValue(result.quotes.toMutableList())
    }

    private suspend fun getFavoriteData() {
        val result = quoteRepository.getFavoriteQuotes()
        _quotes.postValue(result.toMutableList())
    }

    private suspend fun getMyQuotesData() {
        val result = quoteRepository.getCustomQuotes()
        _quotes.postValue(result.toMutableList())
    }

    fun getDataByName(name: String) {
        showLoading()
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            when (name) {
                BaseConstant.CATEGORY_ALL -> getAllRandomData()
                BaseConstant.CATEGORY_FAVORITE -> getFavoriteData()
                BaseConstant.CATEGORY_MY_QUOTE -> getMyQuotesData()
                else -> getData(name)
            }
            hideLoading()
        }
    }

    fun getData(tagName: String = DEFAULT_VALUE_PARAMS) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val quoteData = quoteRepository.getRandomQuotes(
                APIConfig.DEFAULT_ITEM_NUMBER,
                DEFAULT_TYPE_PARAMS,
                tagName
            ).quotes

            quoteData.forEach { quote ->
                quoteRepository.getQuoteId(quote.text, quote.author, quote.tag)?.let {
                    quote.id = it
                }
            }

            val preQuotes = _quotes.value ?: mutableListOf()
            preQuotes.let {
                it.addAll(quoteData)
                _quotes.postValue(it)
            }

            hideLoading()
        }
    }

    fun getDataFromNotify(quote: Quote) {
        showLoading()
        _quotes.value = mutableListOf(quote)
        getData()
        hideLoading()
    }

    fun changeFavoriteStatus(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            if (quote.isFavorite) {
                quote.isFavorite = false
                quoteRepository.updateQuote(quote)
            } else {
                quote.isFavorite = true
                if (quote.id == NOT_IN_LOCAL) {
                    quote.id = quoteRepository.insertQuote(quote)
                } else {
                    quoteRepository.updateQuote(quote)
                }
            }
        }
    }

    companion object {
        private const val NOT_IN_LOCAL = 0L
    }
}
