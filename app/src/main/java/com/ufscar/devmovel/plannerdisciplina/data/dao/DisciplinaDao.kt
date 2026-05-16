package com.ufscar.devmovel.plannerdisciplina.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ufscar.devmovel.plannerdisciplina.model.Disciplina
import com.ufscar.devmovel.plannerdisciplina.model.CampoDisciplina
import kotlinx.coroutines.flow.Flow

@Dao
interface DisciplinaDao {
    @Insert
    suspend fun insertDisciplina(disciplina: Disciplina)

    @Insert
    suspend fun insertCampoDisciplina(campoDisciplina: CampoDisciplina)

    @Query("SELECT * FROM disciplina")
    fun getAllDisciplinas(): Flow<List<Disciplina>>

    @Query("SELECT * FROM campo_disciplina WHERE disciplinaId = :disciplinaId")
    fun getCamposDisciplina(disciplinaId: Int): Flow<List<CampoDisciplina>>

    @Delete
    suspend fun deleteDisciplina(disciplina: Disciplina)
}