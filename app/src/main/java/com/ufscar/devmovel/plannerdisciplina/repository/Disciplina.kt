package com.ufscar.devmovel.plannerdisciplina.repository

import androidx.compose.ui.res.stringResource
import com.ufscar.devmovel.plannerdisciplina.CampoDisciplina
import com.ufscar.devmovel.plannerdisciplina.R

data class Disciplina(
    val id: Int = 0,
    val nome: String = "Nome da Disciplina",
    val professor: String = "",
    val sala: String = "",
    val horario: String = "",
    val progresso: Float = 0.0f,
    val campos: MutableList<CampoDisciplina> = mutableListOf<CampoDisciplina>()
)