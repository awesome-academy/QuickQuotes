package com.sun.quickquotes.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Quote.TABLE_NAME)
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    @SerializedName("text")
    val text: String = "",
    @ColumnInfo
    @SerializedName("author")
    val author: String = "",
    @ColumnInfo
    @SerializedName("tag")
    val tag: String = "",
    @ColumnInfo(defaultValue = "0")
    val isFavorite: Boolean = false,
    @ColumnInfo(defaultValue = "-1")
    val collectionId: Long = -1,
) : Parcelable {

    companion object {
        const val TABLE_NAME = "quotes"

        val diffUtil = object : DiffUtil.ItemCallback<Quote>() {
            override fun areItemsTheSame(oldItem: Quote, newItem: Quote) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Quote, newItem: Quote) =
                oldItem == newItem
        }
    }
}
