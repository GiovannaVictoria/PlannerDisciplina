package com.ufscar.devmovel.plannerdisciplina.ui.components.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import com.ufscar.devmovel.plannerdisciplina.model.CampoDisciplina
import com.ufscar.devmovel.plannerdisciplina.viewmodel.MainViewModel

@Composable
fun RemoverCampoDialog(
    mainViewModel: MainViewModel,
    campoDisciplina: CampoDisciplina
) {
    AlertDialog(
        onDismissRequest = { mainViewModel.fecharRemoverCampoDialog() },
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Text(
                text = stringResource(R.string.confirmacao_remocao_campo),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        text = {
            Text(
                text = stringResource(R.string.explicacao_remocao_campo),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                onClick = { mainViewModel.removerCampo(campoDisciplina) },
                colors = ButtonDefaults.buttonColors(Color.Red),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = stringResource(R.string.remover),
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { mainViewModel.fecharRemoverCampoDialog() },
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
                Icons.Filled.Delete,
                contentDescription = stringResource(R.string.remover_campo),
                tint = Color.Black
            )
        }
    )
}