package com.ufscar.devmovel.plannerdisciplina

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ufscar.devmovel.plannerdisciplina.viewmodel.MainViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MainViewModel(plannerApplication().container.disciplinaRepository)
        }
    }
}

fun CreationExtras.plannerApplication(): PlannerApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as PlannerApplication)