package com.ufscar.devmovel.plannerdisciplina

import retrofit2.http.GET

interface ZenQuotesApiInterface {
    @GET()
    suspend fun getAllQuotes() : List<ZenQuotesResponse>

    @GET("random")
    suspend fun getQuote(): ZenQuotesResponse
}
