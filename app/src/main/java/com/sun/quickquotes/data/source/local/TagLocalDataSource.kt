package com.sun.quickquotes.data.source.local

import com.sun.quickquotes.data.model.Tag
import com.sun.quickquotes.data.source.TagDataSource

class TagLocalDataSource(
    private val tagDAO: TagDAO
) : TagDataSource.Local {

    override suspend fun getLocalTags() =
        tagDAO.getTags()

    override suspend fun insertTag(tag: Tag) =
        tagDAO.insertTag(tag)

    override suspend fun deleteTag(tag: Tag) =
        tagDAO.deleteTag(tag)
}
