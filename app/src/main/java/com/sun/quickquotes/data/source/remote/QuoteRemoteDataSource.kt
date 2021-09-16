package com.sun.quickquotes.data.source.remote

import com.sun.quickquotes.data.source.QuoteDataSource
import com.sun.quickquotes.data.source.remote.utils.APIService

class QuoteRemoteDataSource(
    private val apiService: APIService,
) : QuoteDataSource.Remote {

    override suspend fun getRandomQuotes(count: String) =
        apiService.getRandomQuotes(count)

    override suspend fun getAllQuotes() =
        apiService.getAllQuotes()

    override suspend fun getAllQuotes(type: String, value: String) =
        apiService.getAllQuotes(type, value)
}
