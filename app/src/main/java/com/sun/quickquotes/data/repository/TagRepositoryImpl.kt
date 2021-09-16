package com.sun.quickquotes.data.repository

import com.sun.quickquotes.data.model.Tag
import com.sun.quickquotes.data.source.TagDataSource

class TagRepositoryImpl(
    private val local: TagDataSource.Local,
    private val remote: TagDataSource.Remote
) : TagRepository {

    override suspend fun getLocalTags() =
        local.getLocalTags()

    override suspend fun insertTag(tag: Tag) =
        local.insertTag(tag)

    override suspend fun deleteTag(tag: Tag) =
        local.deleteTag(tag)

    override suspend fun getAllTags() =
        remote.getAllTags()
}
