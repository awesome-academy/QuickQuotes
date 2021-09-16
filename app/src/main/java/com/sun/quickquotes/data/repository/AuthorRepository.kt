package com.sun.quickquotes.data.repository

import com.sun.quickquotes.data.model.Author
import com.sun.quickquotes.data.model.AuthorResponse

interface AuthorRepository {
    suspend fun getLocalAuthors(): List<Author>
    suspend fun insertAuthor(author: Author)
    suspend fun deleteAuthor(author: Author)
    suspend fun getAllAuthors(): AuthorResponse
}
