package com.ufscar.devmovel.plannerdisciplina

import com.google.gson.annotations.SerializedName

data class DummyQuotesResponse (
    @SerializedName("quote")
    val content: String,

    val author: String,
)