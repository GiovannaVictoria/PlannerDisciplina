package com.ufscar.devmovel.plannerdisciplina.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import com.ufscar.devmovel.plannerdisciplina.ui.components.BottomBar
import com.ufscar.devmovel.plannerdisciplina.ui.components.CardDisciplina
import com.ufscar.devmovel.plannerdisciplina.R
import com.ufscar.devmovel.plannerdisciplina.ui.components.TopBarListaDisciplinas
import com.ufscar.devmovel.plannerdisciplina.viewmodel.MainViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ListaDisciplinasScreen(
    mainViewModel: MainViewModel,
    onNavigateToAtualizacaoDisciplinaScreen: (disciplinaId: Int) -> Unit
) {
    Scaffold(
        topBar = { TopBarListaDisciplinas(mainViewModel = mainViewModel) },
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
                    items(mainViewModel.listaDisciplinas) { disciplinaCampos ->
                        CardDisciplina(
                            mainViewModel = mainViewModel,
                            disciplinaCampos = disciplinaCampos,
                            onNavigateToAtualizacaoDisciplinaScreen = {
                                onNavigateToAtualizacaoDisciplinaScreen(disciplinaCampos.disciplina.id)
                            })
                    }
                }
            }
        },
        bottomBar = { BottomBar() }
    )
}
