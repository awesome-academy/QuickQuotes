package com.sun.quickquotes.data.source

import com.sun.quickquotes.data.model.Tag
import com.sun.quickquotes.data.model.TagResponse

interface TagDataSource {

    interface Local {
        suspend fun getLocalTags(): List<Tag>
        suspend fun insertTag(tag: Tag)
        suspend fun deleteTag(tag: Tag)
    }

    interface Remote {
        suspend fun getAllTags(): TagResponse
    }
}
