package com.sun.quickquotes.data.model

import com.google.gson.annotations.SerializedName

data class AuthorResponse(
    @SerializedName("authors")
    val authors: List<Author>
)
