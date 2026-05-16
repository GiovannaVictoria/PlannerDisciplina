package com.ufscar.devmovel.plannerdisciplina.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "campo_disciplina",
    foreignKeys = [
        ForeignKey(
            entity = Disciplina::class,
            parentColumns = ["id"],
            childColumns = ["disciplinaId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CampoDisciplina(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val disciplinaId: Int,
    val nome: String = "",
    val valor: String = ""
)