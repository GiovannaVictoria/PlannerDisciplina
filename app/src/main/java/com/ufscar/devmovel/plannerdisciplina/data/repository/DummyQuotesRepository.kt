package com.ufscar.devmovel.plannerdisciplina.data.repository

import com.ufscar.devmovel.plannerdisciplina.data.network.DummyQuotesResponse
import com.ufscar.devmovel.plannerdisciplina.data.network.RetrofitInstance

class DummyQuotesRepository {
    private val api = RetrofitInstance.api

    suspend fun getAllQuotes(): List<DummyQuotesResponse> {
        return api.getAllQuotes()
    }

    suspend fun getQuote(): DummyQuotesResponse {
        return api.getQuote()
    }
}