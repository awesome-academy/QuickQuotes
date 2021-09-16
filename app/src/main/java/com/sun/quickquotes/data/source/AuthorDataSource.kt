package com.sun.quickquotes.data.source

import com.sun.quickquotes.data.model.Author
import com.sun.quickquotes.data.model.AuthorResponse

interface AuthorDataSource {

    interface Local {
        suspend fun getLocalAuthors(): List<Author>
        suspend fun insertAuthor(author: Author)
        suspend fun deleteAuthor(author: Author)
    }

    interface Remote {
        suspend fun getAllAuthors(): AuthorResponse
    }
}
