package com.sun.quickquotes.data.source.local

import androidx.room.*
import com.sun.quickquotes.data.model.Author

@Dao
interface AuthorDAO {

    @Query("SELECT * FROM ${Author.TABLE_NAME}")
    suspend fun getAuthor(): List<Author>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAuthor(author: Author)

    @Delete
    suspend fun deleteAuthor(author: Author)
}
