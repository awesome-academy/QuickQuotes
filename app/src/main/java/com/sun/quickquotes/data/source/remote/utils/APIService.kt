package com.sun.quickquotes.data.source.remote.utils

import com.sun.quickquotes.data.model.AuthorResponse
import com.sun.quickquotes.data.model.QuoteResponse
import com.sun.quickquotes.data.model.TagResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET(APIConfig.BASE_RANDOM)
    suspend fun getRandomQuotes(
        @Query(APIConfig.PARAM_COUNT) count: String = APIConfig.DEFAULT_ITEM_NUMBER
    ): QuoteResponse

    @GET("${APIConfig.BASE_RANDOM}/{count}?")
    suspend fun getRandomQuotes(
        @Path(APIConfig.PARAM_COUNT) count: String = APIConfig.DEFAULT_ITEM_NUMBER,
        @Query(APIConfig.PARAM_TYPE) type: String,
        @Query(APIConfig.PARAM_VALUE) value : String
    ): QuoteResponse

    @GET("${APIConfig.BASE_ALL}/${APIConfig.BASE_QUOTES}")
    suspend fun getAllQuotes(): QuoteResponse

    @GET(APIConfig.BASE_ALL)
    suspend fun getAllQuotes(
        @Query(APIConfig.PARAM_TYPE) type: String,
        @Query(APIConfig.PARAM_VALUE) value : String
    ): QuoteResponse

    @GET("${APIConfig.BASE_ALL}/${APIConfig.BASE_AUTHORS}")
    suspend fun getAllAuthors(): AuthorResponse

    @GET("${APIConfig.BASE_ALL}/${APIConfig.BASE_TAGS}")
    suspend fun getAllTags(): TagResponse

}
