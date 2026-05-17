package com.ufscar.devmovel.plannerdisciplina.data.repository

import com.ufscar.devmovel.plannerdisciplina.data.dao.DisciplinaDao
import com.ufscar.devmovel.plannerdisciplina.model.CampoDisciplina
import com.ufscar.devmovel.plannerdisciplina.model.Disciplina

class DisciplinaRepository(private val disciplinaDao: DisciplinaDao) {
    suspend fun insertDisciplina(disciplina: Disciplina) = disciplinaDao.insertDisciplina(disciplina)

    suspend fun upsertCampoDisciplina(campoDisciplina: CampoDisciplina) = disciplinaDao.upsertCampoDisciplina(campoDisciplina)

    fun getAllDisciplinas() = disciplinaDao.getAllDisciplinas()

    fun getAllDisciplinaCampos() = disciplinaDao.getAllDisciplinaCampos()

    fun getCamposDisciplina(disciplinaId: Int) = disciplinaDao.getCamposDisciplina(disciplinaId)

    suspend fun deleteDisciplina(disciplina: Disciplina) = disciplinaDao.deleteDisciplina(disciplina)
}