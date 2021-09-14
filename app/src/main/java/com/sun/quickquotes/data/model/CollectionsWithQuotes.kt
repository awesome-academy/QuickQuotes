package com.sun.quickquotes.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class CollectionsWithQuotes(
    @Embedded
    val collection: Collection,
    @Relation(
        parentColumn = "id",
        entityColumn = "collectionId"
    )
    val quotes: List<Quote>
)
