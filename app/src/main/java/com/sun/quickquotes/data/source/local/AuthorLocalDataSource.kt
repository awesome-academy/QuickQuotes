package com.sun.quickquotes.data.source.local

import com.sun.quickquotes.data.model.Author
import com.sun.quickquotes.data.source.AuthorDataSource

class AuthorLocalDataSource(
    private val authorDAO: AuthorDAO
) : AuthorDataSource.Local {

    override suspend fun getLocalAuthors() =
        authorDAO.getAuthor()

    override suspend fun insertAuthor(author: Author) =
        authorDAO.insertAuthor(author)

    override suspend fun deleteAuthor(author: Author) =
        authorDAO.deleteAuthor(author)
}
