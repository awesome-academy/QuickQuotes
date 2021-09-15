package com.sun.quickquotes.data.source.local

import androidx.room.*
import com.sun.quickquotes.data.model.Collection
import com.sun.quickquotes.data.model.CollectionsWithQuotes

@Dao
interface CollectionDAO {

    @Query("SELECT * FROM ${Collection.TABLE_NAME}")
    suspend fun getCollections(): List<Collection>

    @Transaction
    @Query("SELECT * FROM ${Collection.TABLE_NAME} WHERE id = :id")
    suspend fun getCollectionWithQuotes(id: Long): List<CollectionsWithQuotes>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCollection(collection: Collection)

    @Update
    suspend fun updateCollection(collection: Collection)

    @Delete
    suspend fun deleteCollection(collection: Collection)
}
