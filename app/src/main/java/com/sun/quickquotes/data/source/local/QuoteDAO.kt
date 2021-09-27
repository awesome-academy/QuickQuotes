package com.sun.quickquotes.data.source.local

import androidx.room.*
import com.sun.quickquotes.data.model.Quote

@Dao
interface QuoteDAO {

    @Query("SELECT * FROM ${Quote.TABLE_NAME}")
    suspend fun getAllLocalQuotes(): List<Quote>

    @Query("SELECT * FROM ${Quote.TABLE_NAME} WHERE isFavorite = 1")
    suspend fun getFavoriteQuotes(): List<Quote>

    @Query("SELECT * FROM ${Quote.TABLE_NAME} WHERE tag = '$CUSTOM_TAG'")
    suspend fun getCustomQuotes(): List<Quote>

    @Query("SELECT id FROM ${Quote.TABLE_NAME} WHERE text = :text AND author = :author AND tag = :tag")
    suspend fun getQuoteId(
        text: String,
        author: String,
        tag: String
    ): Long?

    @Query("SELECT COUNT(*) FROM ${Quote.TABLE_NAME} WHERE id =:id AND isFavorite = 1")
    suspend fun isFavorite(id: Long): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuote(quote: Quote): Long

    @Update
    suspend fun updateQuote(quote: Quote)

    @Delete
    suspend fun deleteQuote(quote: Quote)

    companion object {
        private const val CUSTOM_TAG = "custom"
    }
}
