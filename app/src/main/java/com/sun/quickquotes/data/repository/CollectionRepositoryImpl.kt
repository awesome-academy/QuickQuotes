package com.sun.quickquotes.data.repository

import com.sun.quickquotes.data.model.Collection
import com.sun.quickquotes.data.source.CollectionDataSource

class CollectionRepositoryImpl(
    private val local: CollectionDataSource.Local
) : CollectionRepository {

    override suspend fun getCollections() =
        local.getCollections()

    override suspend fun getCollectionWithQuotes(id: Long) =
        local.getCollectionWithQuotes(id)

    override suspend fun insertCollection(collection: Collection) =
        local.insertCollection(collection)

    override suspend fun updateCollection(collection: Collection) =
        local.updateCollection(collection)

    override suspend fun deleteCollection(collection: Collection) =
        local.deleteCollection(collection)
}
