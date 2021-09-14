package com.sun.quickquotes.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = Collection.TABLE_NAME,
    indices = [Index(value = ["name"], unique = true)]
)
data class Collection(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo
    val name: String,
) : Parcelable {

    companion object {
        const val TABLE_NAME = "collection"

        val diffUtil = object : DiffUtil.ItemCallback<Collection>() {
            override fun areItemsTheSame(oldItem: Collection, newItem: Collection) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Collection, newItem: Collection) =
                oldItem == newItem
        }
    }
}
