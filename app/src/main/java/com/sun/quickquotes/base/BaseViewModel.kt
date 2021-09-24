package com.sun.quickquotes.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.quickquotes.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Event<Boolean>>().apply { value = Event(false) }
    val isLoading: LiveData<Event<Boolean>>
        get() = _isLoading
    private val _message = MutableLiveData<Event<@StringRes Int>>()
    val message: MutableLiveData<Event<Int>>
        get() = _message

    protected val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            _message.value = Event(R.string.error_something_wrong)
            throwable.printStackTrace()
        }
    }

    fun showLoading() {
        _isLoading.value = Event(true)
    }

    fun hideLoading() {
        _isLoading.postValue(Event(false))
    }
}
