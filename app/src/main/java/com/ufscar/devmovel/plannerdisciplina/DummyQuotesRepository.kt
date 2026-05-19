package com.ufscar.devmovel.plannerdisciplina

class DummyQuotesRepository {
    private val api = RetrofitInstance.api

    suspend fun getAllQuotes(): List<DummyQuotesResponse> {
        return api.getAllQuotes()
    }

    suspend fun getQuote(): DummyQuotesResponse {
        return api.getQuote()
    }
}