package com.ufscar.devmovel.plannerdisciplina

class ZenQuotesRepository {
    private val api = RetrofitInstance.api

    suspend fun getAllQuotes(): List<ZenQuotesResponse> {
        return api.getAllQuotes()
    }

    suspend fun getQuote(): ZenQuotesResponse {
        return api.getQuote()
    }
}