package com.ufscar.devmovel.plannerdisciplina

import com.google.gson.annotations.SerializedName

data class ZenQuotesResponse (
    @SerializedName("quote")
    val content: String,

    val author: String,

//    @SerializedName("i")
//    val authorImage: String,

//    @SerializedName("c")
//    val characterCount: String,

//    @SerializedName("h")
//    val preFormattedHtmlQuote: String
)