package com.sun.quickquotes.data.repository

import com.sun.quickquotes.data.model.Quote
import com.sun.quickquotes.data.model.QuoteResponse

interface QuoteRepository {
    suspend fun getAllLocalQuotes(): List<Quote>

    suspend fun getFavoriteQuotes(): List<Quote>

    suspend fun getCustomQuotes(): List<Quote>

    suspend fun getQuoteId(
        text: String,
        author: String,
        tag: String
    ): Long

    suspend fun isFavorite(id: Long): Boolean

    suspend fun insertQuote(quote: Quote)

    suspend fun updateQuote(quote: Quote)

    suspend fun deleteQuote(quote: Quote)

    suspend fun getRandomQuotes(count: String): QuoteResponse

    suspend fun getAllQuotes(): QuoteResponse

    suspend fun getAllQuotes(
        type: String,
        value: String
    ): QuoteResponse
}
