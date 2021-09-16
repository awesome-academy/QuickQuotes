package com.sun.quickquotes.data.source

import com.sun.quickquotes.data.model.Collection
import com.sun.quickquotes.data.model.CollectionsWithQuotes

interface CollectionDataSource {

    interface Local {
        suspend fun getCollections(): List<Collection>
        suspend fun getCollectionWithQuotes(id: Long): List<CollectionsWithQuotes>
        suspend fun insertCollection(collection: Collection)
        suspend fun updateCollection(collection: Collection)
        suspend fun deleteCollection(collection: Collection)
    }
}
