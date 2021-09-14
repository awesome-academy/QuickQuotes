package com.sun.quickquotes.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = Author.TABLE_NAME,
    indices = [Index(value = ["name"], unique = true)]
)
data class Author(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo
    @SerializedName("name")
    val name: String,
    @ColumnInfo
    @SerializedName("count")
    val count: String,
) : Parcelable {

    companion object {
        const val TABLE_NAME = "author"

        val diffUtil = object : DiffUtil.ItemCallback<Author>() {
            override fun areItemsTheSame(oldItem: Author, newItem: Author) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Author, newItem: Author) =
                oldItem == newItem
        }
    }
}
