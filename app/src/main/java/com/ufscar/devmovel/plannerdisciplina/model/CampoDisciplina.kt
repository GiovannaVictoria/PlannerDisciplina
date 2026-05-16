package com.ufscar.devmovel.plannerdisciplina.model

import kotlinx.serialization.Serializable

@Serializable
data class CampoDisciplina(
    val nome: String = "",
    val valor: String = ""
)