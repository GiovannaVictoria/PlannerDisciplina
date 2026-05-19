package com.ufscar.devmovel.plannerdisciplina

import android.content.Context
import com.ufscar.devmovel.plannerdisciplina.data.AppDatabase
import com.ufscar.devmovel.plannerdisciplina.data.repository.DisciplinaRepository

class AppContainer(private var context: Context) {
    val disciplinaRepository: DisciplinaRepository by lazy {
        DisciplinaRepository(AppDatabase.getDatabase(context).disciplinaDao())
    }

    val dummyQuotesRepository: DummyQuotesRepository by lazy {
        DummyQuotesRepository()
    }
}