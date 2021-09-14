package com.sun.quickquotes.data.model

import com.google.gson.annotations.SerializedName

data class TagResponse(
    @SerializedName("authors")
    val authors: List<Tag>
)
