package com.ufscar.devmovel.plannerdisciplina.navigation

import kotlinx.serialization.Serializable

@Serializable
object ListaDisciplinas

@Serializable
data class AtualizacaoDisciplina(val disciplinaId: Int)
