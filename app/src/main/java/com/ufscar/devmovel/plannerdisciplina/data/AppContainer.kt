package com.ufscar.devmovel.plannerdisciplina.data

import android.content.Context
import com.ufscar.devmovel.plannerdisciplina.data.repository.DisciplinaRepository
import com.ufscar.devmovel.plannerdisciplina.data.repository.DummyQuotesRepository

class AppContainer(private var context: Context) {
    val disciplinaRepository: DisciplinaRepository by lazy {
        DisciplinaRepository(AppDatabase.getDatabase(context).disciplinaDao())
    }

    val dummyQuotesRepository: DummyQuotesRepository by lazy {
        DummyQuotesRepository()
    }
}