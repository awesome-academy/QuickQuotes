package com.sun.quickquotes.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.quickquotes.base.BaseViewModel
import com.sun.quickquotes.data.model.Tag
import com.sun.quickquotes.data.repository.TagRepository

class CategoryViewModel(
    private val tagRepository: TagRepository
) : BaseViewModel() {

    private val _tags = MutableLiveData<MutableList<Tag>>()
    val tags: LiveData<MutableList<Tag>>
        get() = _tags

    init {
        getData()
    }

    private fun getData() {

    }

}
