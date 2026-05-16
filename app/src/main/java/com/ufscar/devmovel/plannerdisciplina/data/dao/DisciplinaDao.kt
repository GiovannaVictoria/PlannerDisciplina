package com.ufscar.devmovel.plannerdisciplina.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ufscar.devmovel.plannerdisciplina.model.Disciplina
import kotlinx.coroutines.flow.Flow

@Dao
interface DisciplinaDao {
    @Insert
    suspend fun insertDisciplina(disciplina: Disciplina)

    @Query("SELECT * FROM Disciplina")
    fun getAllDisciplinas(): Flow<List<Disciplina>>

    @Delete
    suspend fun deleteDisciplina(disciplina: Disciplina)
}