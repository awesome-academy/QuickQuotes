package com.sun.quickquotes.data.source.local

import com.sun.quickquotes.data.model.Quote
import com.sun.quickquotes.data.source.QuoteDataSource

class QuoteLocalDataSource(
    private val quoteDAO: QuoteDAO
) : QuoteDataSource.Local {

    override suspend fun getAllLocalQuotes() =
        quoteDAO.getAllLocalQuotes()

    override suspend fun getFavoriteQuotes() =
        quoteDAO.getFavoriteQuotes()

    override suspend fun getCustomQuotes() =
        quoteDAO.getCustomQuotes()

    override suspend fun getQuoteId(text: String, author: String, tag: String) =
        quoteDAO.getQuoteId(text, author, tag)

    override suspend fun isFavorite(id: Long) =
        quoteDAO.isFavorite(id)

    override suspend fun insertQuote(quote: Quote) =
        quoteDAO.insertQuote(quote)

    override suspend fun updateQuote(quote: Quote) =
        quoteDAO.updateQuote(quote)

    override suspend fun deleteQuote(quote: Quote) =
        quoteDAO.deleteQuote(quote)
}
