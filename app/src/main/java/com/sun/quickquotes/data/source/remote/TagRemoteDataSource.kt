package com.sun.quickquotes.data.source.remote

import com.sun.quickquotes.data.source.TagDataSource
import com.sun.quickquotes.data.source.remote.utils.APIService

class TagRemoteDataSource(
    private val apiService: APIService
) : TagDataSource.Remote {

    override suspend fun getAllTags() =
        apiService.getAllTags()
}
