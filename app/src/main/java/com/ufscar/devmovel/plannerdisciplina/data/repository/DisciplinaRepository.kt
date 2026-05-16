package com.ufscar.devmovel.plannerdisciplina.data.repository

import com.ufscar.devmovel.plannerdisciplina.data.dao.DisciplinaDao
import com.ufscar.devmovel.plannerdisciplina.model.Disciplina

class DisciplinaRepository(private val disciplinaDao: DisciplinaDao) {
    suspend fun insertDisciplina(disciplina: Disciplina) = disciplinaDao.insertDisciplina(disciplina)

    fun getAllDisciplinas() = disciplinaDao.getAllDisciplinas()

    suspend fun deleteDisciplina(disciplina: Disciplina) = disciplinaDao.deleteDisciplina(disciplina)
}