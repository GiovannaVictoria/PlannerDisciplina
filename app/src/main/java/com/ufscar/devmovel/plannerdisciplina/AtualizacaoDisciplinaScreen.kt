package com.ufscar.devmovel.plannerdisciplina

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AtualizacaoDisciplinaScreen(
    mainViewModel: MainViewModel,
    disciplinaId: Int,
    onNavigateToListaDisciplinas: () -> Unit
) {
    val disciplina = mainViewModel.listaDisciplinas.find { it.id == disciplinaId }

    // TODO: Melhorar a pagina de "Disciplina nao encontrada"
    if (disciplina == null) {
        Text("Disciplina não encontrada")
        return
    }

    Scaffold(
        topBar = { TopBarAtualizacaoDisciplinas(mainViewModel = mainViewModel) },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(R.drawable.fundo_papel),
                    contentDescription = stringResource(R.string.fundo_papel),
                    contentScale = ContentScale.Crop
                )
                Card(
                    modifier = Modifier
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.adicionar_imagem_default),
                                contentDescription = stringResource(id = R.string.icone_adicionar_imagem_default),
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Black, CircleShape)
                            )
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            ) {
                                BasicTextField(
                                    value = mainViewModel.estadoTemporarioDisciplina.disciplina.nome,
                                    onValueChange = { mainViewModel.alterarEstadoTemporarioNome(it) },
                                    textStyle = TextStyle(
                                        textAlign = TextAlign.Center,
                                        color = Color.Black,
                                        fontSize = 24.sp
                                    )
                                )
                                LinearProgressIndicator(
                                    progress = { disciplina.progresso },
                                    modifier = Modifier
                                        .height(16.dp)
                                        .border(1.dp, Color.Black),
                                    gapSize = 0.dp,
                                    strokeCap = StrokeCap.Butt,
                                    drawStopIndicator = {},
                                    trackColor = Color.LightGray,
                                    color = Color(0xFF06B004)
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(R.string.campo),
                                color = Color.White,
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .background(Color(0xFF01037A), RoundedCornerShape(6.dp))
                                    .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
                                    .padding(8.dp)
                            )
                            Text(
                                text = stringResource(R.string.acoes),
                                color = Color.White,
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .background(Color(0xFF01037A), RoundedCornerShape(6.dp))
                                    .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
                                    .padding(8.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                        ) {
                            mainViewModel.estadoTemporarioDisciplina.disciplina.campos.forEach { campo ->
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(4.dp)
                                ) {
                                    OutlinedTextField(
                                        value = mainViewModel.estadoTemporarioDisciplina.disciplina.campos.find{ it.nome == campo.nome }?.valor ?: "",
                                        onValueChange = { novoValor ->
                                            mainViewModel.alterarEstadoTemporarioCampoValor(campo.nome, novoValor)
                                        },
                                        label = {
                                            Text(
                                                text = campo.nome,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                            )
                                        },
                                        textStyle = TextStyle(
                                            textAlign = TextAlign.Center,
                                            color = Color.Black
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(0.4f),
                                        colors = TextFieldDefaults.colors(
                                            focusedContainerColor = Color.White,
                                            unfocusedContainerColor = Color.White,
                                            disabledContainerColor = Color.White
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    TooltipBox(
                                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                                        tooltip = { PlainTooltip { Text(text = stringResource(R.string.editar)) } },
                                        state = rememberTooltipState()
                                    ) {
                                        IconButton(onClick = {}) {
                                            Icon(
                                                Icons.Filled.Edit,
                                                contentDescription = stringResource(R.string.editar),
                                                tint = Color.Black
                                            )
                                        }
                                    }
                                    TooltipBox(
                                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                                        tooltip = { PlainTooltip { Text(text = stringResource(R.string.remover)) } },
                                        state = rememberTooltipState()
                                    ) {
                                        IconButton(
                                            onClick = { mainViewModel.removerCampo(campo.nome) }
                                        ) {
                                            Icon(
                                                Icons.Filled.Delete,
                                                contentDescription = stringResource(R.string.remover),
                                                tint = Color.Black
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    mainViewModel.salvarAlteracoesDisciplina()
                                    onNavigateToListaDisciplinas()
                                },
                                colors = ButtonDefaults.buttonColors(Color(0xFF06B004)),
                                contentPadding = PaddingValues(8.dp),
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, Color.Black)
                            ) {
                                Text(
                                    text = stringResource(R.string.salvar),
                                    color = Color.White
                                )
                            }
                            Button(
                                onClick = {
                                    mainViewModel.abrirAdicaoCampoDialog()
                                },
                                colors = ButtonDefaults.buttonColors(Color.White),
                                contentPadding = PaddingValues(8.dp),
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, Color.Black)
                            ) {
                                Text(
                                    text = stringResource(R.string.novo_campo),
                                    color = Color.Black
                                )
                            }
                            Button(
                                onClick = { onNavigateToListaDisciplinas() },
                                colors = ButtonDefaults.buttonColors(Color.Red),
                                contentPadding = PaddingValues(8.dp),
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, Color.Black)
                            ) {
                                Text(
                                    text = stringResource(R.string.cancelar),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
                if (mainViewModel.adicaoCampoDialogAberto) {
                    AdicaoCampoDialog(mainViewModel = mainViewModel)
                }
            }
        },
        bottomBar = { BottomBar() }
    )
}
