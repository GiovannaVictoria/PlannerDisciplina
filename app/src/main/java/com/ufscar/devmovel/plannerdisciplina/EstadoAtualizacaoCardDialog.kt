package com.ufscar.devmovel.plannerdisciplina

import com.ufscar.devmovel.plannerdisciplina.repository.Disciplina

data class EstadoAtualizacaoCardDialog(
    val disciplina: Disciplina = Disciplina(),
)