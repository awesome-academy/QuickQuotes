package com.sun.quickquotes.data.repository

import com.sun.quickquotes.data.model.Quote
import com.sun.quickquotes.data.model.QuoteResponse
import com.sun.quickquotes.data.source.QuoteDataSource

class QuoteRepositoryImpl(
    private val local: QuoteDataSource.Local,
    private val remote: QuoteDataSource.Remote
) : QuoteRepository {

    override suspend fun getAllLocalQuotes() =
        local.getAllLocalQuotes()

    override suspend fun getFavoriteQuotes() =
        local.getFavoriteQuotes()

    override suspend fun getCustomQuotes() =
        local.getCustomQuotes()

    override suspend fun getQuoteId(text: String, author: String, tag: String) =
        local.getQuoteId(text, author, tag)

    override suspend fun isFavorite(id: Long) =
        local.isFavorite(id)

    override suspend fun insertQuote(quote: Quote) =
        local.insertQuote(quote)

    override suspend fun updateQuote(quote: Quote) =
        local.updateQuote(quote)

    override suspend fun deleteQuote(quote: Quote) =
        local.deleteQuote(quote)

    override suspend fun getRandomQuotes(count: String) =
        remote.getRandomQuotes(count)

    override suspend fun getRandomQuotes(
        count: String,
        type: String,
        value: String
    ) = remote.getRandomQuotes(count, type, value)

    override suspend fun getAllQuotes() =
        remote.getAllQuotes()

    override suspend fun getAllQuotes(type: String, value: String) =
        remote.getAllQuotes(type, value)
}
