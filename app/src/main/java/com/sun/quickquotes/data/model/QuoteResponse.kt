package com.sun.quickquotes.data.model

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("quotes")
    val quotes: List<Quote>
)
