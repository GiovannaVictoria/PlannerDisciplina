package com.ufscar.devmovel.plannerdisciplina

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ufscar.devmovel.plannerdisciplina.navigation.AtualizacaoCard
import com.ufscar.devmovel.plannerdisciplina.navigation.ListaDisciplinas
import com.ufscar.devmovel.plannerdisciplina.ui.screens.AtualizacaoDisciplinaScreen
import com.ufscar.devmovel.plannerdisciplina.ui.screens.ListaDisciplinasScreen
import com.ufscar.devmovel.plannerdisciplina.viewmodel.MainViewModel

@Composable
fun App() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory)
    NavHost(
        navController = navController,
        startDestination = ListaDisciplinas
    ) {
        composable<ListaDisciplinas> {
            ListaDisciplinasScreen(
                mainViewModel = mainViewModel,
                onNavigateToAtualizacaoDisciplinaScreen = { disciplinaId ->
                    val disciplina = mainViewModel.listaDisciplinas.find { it.id == disciplinaId }
                    if (disciplina != null) {
                        mainViewModel.setarDisciplinaSendoEditada(disciplina)
                        navController.navigate(AtualizacaoCard(disciplinaId = disciplinaId))
                    }
                }
            )
        }

        composable<AtualizacaoCard> { backStackEntry ->
            val rota = backStackEntry.toRoute<AtualizacaoCard>()

            AtualizacaoDisciplinaScreen(
                mainViewModel = mainViewModel,
                disciplinaId = rota.disciplinaId,
                onNavigateToListaDisciplinas = {
                    navController.popBackStack()
                }
            )
        }
    }
}