package com.example.taskmate.data.repository

import com.example.taskmate.data.model.Quote
import com.example.taskmate.data.remote.QuoteApiService

interface QuoteRepository {
    suspend fun getQuote(): Quote?
}

class NetworkQuoteRepository(
    private val api: QuoteApiService,
) : QuoteRepository {
    override suspend fun getQuote(): Quote? = api.getQuote().firstOrNull()
}
