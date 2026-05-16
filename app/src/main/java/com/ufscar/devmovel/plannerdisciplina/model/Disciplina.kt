package com.ufscar.devmovel.plannerdisciplina.model

data class Disciplina(
    val id: Int = 0,
    val nome: String = "Nome da Disciplina",
    val professor: String = "",
    val sala: String = "",
    val horario: String = "",
    val progresso: Float = 0.0f,
    val campos: List<CampoDisciplina> = listOf<CampoDisciplina>()
)