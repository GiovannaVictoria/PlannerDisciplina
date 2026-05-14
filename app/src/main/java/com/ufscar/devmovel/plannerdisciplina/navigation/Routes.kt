package com.ufscar.devmovel.plannerdisciplina.navigation

import com.ufscar.devmovel.plannerdisciplina.repository.Disciplina
import kotlinx.serialization.Serializable

@Serializable
object ListaDisciplinas

@Serializable
data class AtualizacaoCard(val disciplinaId: Int)
