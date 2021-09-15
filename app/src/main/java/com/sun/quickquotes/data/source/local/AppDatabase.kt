package com.sun.quickquotes.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sun.quickquotes.data.model.Author
import com.sun.quickquotes.data.model.Collection
import com.sun.quickquotes.data.model.Quote
import com.sun.quickquotes.data.model.Tag
import com.sun.quickquotes.data.source.local.AppDatabase.Companion.DATABASE_VERSION
import com.sun.quickquotes.data.source.local.AppDatabase.Companion.EXPORT_SCHEME

@Database(
    entities = [Author::class,
        Collection::class,
        Quote::class,
        Tag::class],
    version = DATABASE_VERSION,
    exportSchema = EXPORT_SCHEME
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun authorDao(): AuthorDAO
    abstract fun collectionDao(): CollectionDAO
    abstract fun quoteDao(): QuoteDAO
    abstract fun tagDao(): TagDAO

    companion object {
        const val DATABASE_NAME = "QuickQuotes"
        const val DATABASE_VERSION = 1
        const val EXPORT_SCHEME = false
    }
}
