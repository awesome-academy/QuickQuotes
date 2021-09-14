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
    tableName = Tag.TABLE_NAME,
    indices = [Index(value = ["name"], unique = true)]
)
data class Tag(
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
        const val TABLE_NAME = "tag"

        val diffUtil = object : DiffUtil.ItemCallback<Tag>() {
            override fun areItemsTheSame(oldItem: Tag, newItem: Tag) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Tag, newItem: Tag) =
                oldItem == newItem
        }
    }
}
