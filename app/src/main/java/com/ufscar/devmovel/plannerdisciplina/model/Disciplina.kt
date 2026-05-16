package com.ufscar.devmovel.plannerdisciplina.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disciplina")
data class Disciplina(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String = "Nome da Disciplina",
//    val professor: String = "",
//    val sala: String = "",
//    val horario: String = "",
    val progresso: Float = 0.0f,
//    val campos: List<CampoDisciplina> = listOf<CampoDisciplina>()
)