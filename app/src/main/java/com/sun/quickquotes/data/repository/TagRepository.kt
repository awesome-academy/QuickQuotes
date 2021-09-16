package com.sun.quickquotes.data.repository

import com.sun.quickquotes.data.model.Tag
import com.sun.quickquotes.data.model.TagResponse

interface TagRepository {
    suspend fun getLocalTags(): List<Tag>
    suspend fun insertTag(tag: Tag)
    suspend fun deleteTag(tag: Tag)
    suspend fun getAllTags(): TagResponse
}
