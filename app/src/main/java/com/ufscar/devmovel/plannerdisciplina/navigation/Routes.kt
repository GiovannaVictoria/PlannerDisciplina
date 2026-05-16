package com.ufscar.devmovel.plannerdisciplina.navigation

import kotlinx.serialization.Serializable

@Serializable
object ListaDisciplinas

@Serializable
data class AtualizacaoCard(val disciplinaId: Int)
