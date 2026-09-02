package com.example.taskmate.data.remote

import com.example.taskmate.data.model.Quote
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApiService {
    @GET("api/json.php")
    suspend fun getQuote(
        @Query("c") count: Int = 1,
        @Query("e") escape: Int = 1,
    ): List<Quote>
}
