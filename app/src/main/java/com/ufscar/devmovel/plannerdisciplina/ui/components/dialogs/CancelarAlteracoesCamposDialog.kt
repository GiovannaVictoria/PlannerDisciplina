package com.ufscar.devmovel.plannerdisciplina.ui.components.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ufscar.devmovel.plannerdisciplina.R
import com.ufscar.devmovel.plannerdisciplina.viewmodel.MainViewModel

@Composable
fun CancelarAlteracoesCamposDialog(
    mainViewModel: MainViewModel,
    onNavigateToListaDisciplinas: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { mainViewModel.fecharCancelarAlteracoesCamposDialog() },
        title = {
            Text(
                text = stringResource(R.string.confirmacao_cancelamento_alteracoes_campos),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        text = {
            Text(
                text = stringResource(R.string.explicacao_cancelamento_alteracoes_campos),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    mainViewModel.fecharCancelarAlteracoesCamposDialog()
                    onNavigateToListaDisciplinas()
                },
                colors = ButtonDefaults.buttonColors(Color.Red),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = stringResource(R.string.cancelar),
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { mainViewModel.fecharCancelarAlteracoesCamposDialog() },
                colors = ButtonDefaults.buttonColors(Color.White),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = stringResource(R.string.voltar),
                    color = Color.Red
                )
            }
        },
        containerColor = Color.White,
        titleContentColor = Color.Red,
        textContentColor = Color.Black,
        shape = RoundedCornerShape(10.dp),
        icon = {
            Icon(
                Icons.Filled.Warning,
                contentDescription = stringResource(R.string.cancelar_alteracoes_campos),
                tint = Color.Black
            )
        }
    )
}
