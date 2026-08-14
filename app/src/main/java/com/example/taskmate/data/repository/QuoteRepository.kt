package com.example.taskmate.data.repository

import com.example.taskmate.data.model.Quote
import com.example.taskmate.data.remote.RetrofitInstance

class QuoteRepository {

    suspend fun getQuote(): Quote {
        return RetrofitInstance.api.getQuote().first()
    }
}