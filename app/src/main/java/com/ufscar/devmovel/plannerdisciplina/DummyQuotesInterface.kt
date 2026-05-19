package com.ufscar.devmovel.plannerdisciplina

import retrofit2.http.GET

interface DummyQuotesInterface {
    @GET()
    suspend fun getAllQuotes() : List<DummyQuotesResponse>

    @GET("random")
    suspend fun getQuote(): DummyQuotesResponse
}
