package com.sun.quickquotes.data.source.local

import com.sun.quickquotes.data.model.Collection
import com.sun.quickquotes.data.source.CollectionDataSource

class CollectionLocalDataSource(
    private val collectionDAO: CollectionDAO
) : CollectionDataSource.Local {

    override suspend fun getCollections() =
        collectionDAO.getCollections()

    override suspend fun getCollectionWithQuotes(id: Long) =
        collectionDAO.getCollectionWithQuotes(id)

    override suspend fun insertCollection(collection: Collection) =
        collectionDAO.insertCollection(collection)

    override suspend fun updateCollection(collection: Collection) =
        collectionDAO.updateCollection(collection)

    override suspend fun deleteCollection(collection: Collection) =
        collectionDAO.deleteCollection(collection)
}
