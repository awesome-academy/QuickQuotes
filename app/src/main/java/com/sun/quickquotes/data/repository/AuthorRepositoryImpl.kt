package com.sun.quickquotes.data.repository

import com.sun.quickquotes.data.model.Author
import com.sun.quickquotes.data.source.AuthorDataSource

class AuthorRepositoryImpl(
    private val local: AuthorDataSource.Local,
    private val remote: AuthorDataSource.Remote
) : AuthorRepository {

    override suspend fun getLocalAuthors() =
        local.getLocalAuthors()

    override suspend fun insertAuthor(author: Author) =
        local.insertAuthor(author)

    override suspend fun deleteAuthor(author: Author) =
        local.deleteAuthor(author)

    override suspend fun getAllAuthors() =
        remote.getAllAuthors()
}
