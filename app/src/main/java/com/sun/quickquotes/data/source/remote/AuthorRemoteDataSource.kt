package com.sun.quickquotes.data.source.remote

import com.sun.quickquotes.data.source.AuthorDataSource
import com.sun.quickquotes.data.source.remote.utils.APIService

class AuthorRemoteDataSource(
    private val apiService: APIService
) : AuthorDataSource.Remote {

    override suspend fun getAllAuthors() =
        apiService.getAllAuthors()
}
