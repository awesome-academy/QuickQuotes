package com.sun.quickquotes.data.source.local

import androidx.room.*
import com.sun.quickquotes.data.model.Tag

@Dao
interface TagDAO {

    @Query("SELECT * FROM ${Tag.TABLE_NAME}")
    suspend fun getTags(): List<Tag>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTag(tag: Tag)

    @Delete
    suspend fun deleteTag(tag: Tag)
}
