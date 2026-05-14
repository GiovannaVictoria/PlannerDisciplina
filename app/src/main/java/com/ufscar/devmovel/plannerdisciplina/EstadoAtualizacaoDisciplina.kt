package com.ufscar.devmovel.plannerdisciplina

import com.ufscar.devmovel.plannerdisciplina.repository.Disciplina

data class EstadoAtualizacaoDisciplina(
    val disciplina: Disciplina = Disciplina(),
)