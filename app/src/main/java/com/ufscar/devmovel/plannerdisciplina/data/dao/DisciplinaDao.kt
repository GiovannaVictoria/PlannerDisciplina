package com.ufscar.devmovel.plannerdisciplina.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.ufscar.devmovel.plannerdisciplina.model.Disciplina
import com.ufscar.devmovel.plannerdisciplina.model.CampoDisciplina
import com.ufscar.devmovel.plannerdisciplina.model.DisciplinaCampos
import kotlinx.coroutines.flow.Flow

@Dao
interface DisciplinaDao {
    @Insert
    suspend fun insertDisciplina(disciplina: Disciplina)

    @Upsert
    suspend fun upsertCampoDisciplina(campoDisciplina: CampoDisciplina)

    @Query("UPDATE disciplina SET nome = :nome WHERE id = :id")
    suspend fun updateNomeDisciplina(id: Int, nome: String)

    @Query("SELECT * FROM disciplina")
    fun getAllDisciplinas(): Flow<List<Disciplina>>

    @Transaction
    @Query("SELECT * FROM disciplina")
    fun getAllDisciplinaCampos(): Flow<List<DisciplinaCampos>>

    @Query("SELECT * FROM campo_disciplina WHERE disciplinaId = :disciplinaId")
    fun getCamposDisciplina(disciplinaId: Int): Flow<List<CampoDisciplina>>

    @Delete
    suspend fun deleteDisciplina(disciplina: Disciplina)

    @Delete
    suspend fun deleteCampoDisciplina(campoDisciplina: CampoDisciplina)
}