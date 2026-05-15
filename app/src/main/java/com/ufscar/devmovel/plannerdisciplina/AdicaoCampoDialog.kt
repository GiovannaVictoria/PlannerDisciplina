package com.ufscar.devmovel.plannerdisciplina

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AdicaoCampoDialog(
    mainViewModel: MainViewModel = MainViewModel(),
) {
    Dialog(
        onDismissRequest = { mainViewModel.fecharAdicaoCampoDialog() }
    ) {
        Card(
            modifier = Modifier
                .padding(4.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.adicao_campo),
                    color = Color.White,
                    modifier = Modifier
                        .background(Color(0xFF01037A), RoundedCornerShape(6.dp))
                        .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
                        .padding(8.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = mainViewModel.campoTemporario.nome,
                    onValueChange = { mainViewModel.alterarCampoTemporarioNome(it) },
                    isError = mainViewModel.nomeCampoErro,
                    supportingText = {
                        if (mainViewModel.nomeCampoErro) {
                            Text(
                                text = mainViewModel.nomeCampoErroMensagem,
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.nome_campo),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            color = Color.Black
                    )
                )
                OutlinedTextField(
                    value = mainViewModel.campoTemporario.valor,
                    onValueChange = { mainViewModel.alterarCampoTemporarioValor(it) },
                    label = {
                        Text(
                            text = stringResource(R.string.valor_campo),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = { mainViewModel.salvarAlteracoesCampo() },
                        colors = ButtonDefaults.buttonColors(Color(0xFF06B004)),
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.adicionar),
                            color = Color.White
                        )
                    }
                    Button(
                        onClick = { mainViewModel.fecharAdicaoCampoDialog() },
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.cancelar),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
