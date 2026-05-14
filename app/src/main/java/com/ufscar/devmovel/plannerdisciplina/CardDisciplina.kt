package com.ufscar.devmovel.plannerdisciplina

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ufscar.devmovel.plannerdisciplina.repository.Disciplina

@Composable
fun CardDisciplina(
    disciplina: Disciplina = Disciplina(),
    mainViewModel: MainViewModel,
    onNavigateToAtualizacaoDisciplinaScreen: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp),
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
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.adicionar_imagem_default),
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
                        .padding(4.dp)
                ) {
                    Text(
                        text = disciplina.nome,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
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
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(4.dp)
            ) {
                OutlinedTextField(
                    value = disciplina.professor,
                    onValueChange = {},
                    label = {
                        Text(
                            text = stringResource(R.string.professor),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    ),
                    readOnly = true,
                    modifier = Modifier
                        .weight(1f)
                )
                OutlinedTextField(
                    value = disciplina.sala,
                    onValueChange = {},
                    label = {
                        Text(
                            text = stringResource(R.string.sala),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    ),
                    readOnly = true,
                    modifier = Modifier
                        .weight(1f)
                )
                OutlinedTextField(
                    value = disciplina.horario,
                    onValueChange = {},
                    label = {
                        Text(
                            text = stringResource(R.string.horario),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    ),
                    readOnly = true,
                    modifier = Modifier
                        .weight(1f)
                )
            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                disciplina.campos.forEach { campo ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        OutlinedTextField(
                            value = campo.valor,
                            onValueChange = {},
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
                            readOnly = true
                        )
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
                    onClick = { onNavigateToAtualizacaoDisciplinaScreen() },
                    colors = ButtonDefaults.buttonColors(Color(0xFF06B004)),
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.atualizar_campos),
                        color = Color.White
                    )
                }
                Button(
                    onClick = { mainViewModel.removerDisciplina(disciplina) },
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.remover_disciplina),
                        color = Color.White
                    )
                }
            }
        }
    }
}
