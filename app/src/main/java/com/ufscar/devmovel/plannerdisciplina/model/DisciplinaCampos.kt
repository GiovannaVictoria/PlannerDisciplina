package com.ufscar.devmovel.plannerdisciplina.model

import androidx.room.Embedded
import androidx.room.Relation

data class DisciplinaCampos(
    @Embedded
    val disciplina: Disciplina,

    @Relation(
        parentColumn = "id",
        entityColumn = "disciplinaId"
    )
    val campos: List<CampoDisciplina>
)