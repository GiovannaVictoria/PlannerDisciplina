package com.ufscar.devmovel.plannerdisciplina

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.ufscar.devmovel.plannerdisciplina.ui.theme.PlannerDisciplinaTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TelaPrincipal(
    mainViewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar() },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(R.drawable.fundo_papel),
                    contentDescription = stringResource(R.string.fundo_papel),
                    contentScale = ContentScale.Crop
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    items(mainViewModel.listaDisciplinas) { disciplina ->
                        CardDisciplina(disciplina)
                    }
                }
                if (mainViewModel.disciplinaSendoEditada != null) {
                    AtualizacaoCardDialog(disciplina = mainViewModel.disciplinaSendoEditada!!)
                }
            }
        },
        bottomBar = { BottomBar() }
    ) {
        NavHost(
            navController = navController,
            startDestination = TelaPrincipal
        ) {
            composable<TelaPrincipal> {
                TelaPrincipal(
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPrincipalPreview() {
    PlannerDisciplinaTheme {
        TelaPrincipal()
    }
}