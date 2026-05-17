package com.ufscar.devmovel.plannerdisciplina.ui.components

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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ufscar.devmovel.plannerdisciplina.R
import com.ufscar.devmovel.plannerdisciplina.model.Disciplina
import com.ufscar.devmovel.plannerdisciplina.viewmodel.MainViewModel

@Composable
fun ConfirmacaoRemocaoDisciplinaDialog(
    mainViewModel: MainViewModel,
    disciplina: Disciplina
) {
    AlertDialog(
        onDismissRequest = { mainViewModel.fecharConfirmacaoRemocaoDisciplinaDialog() },
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Text(
                text = stringResource(R.string.confirmacao_remocao_disciplina),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        text = {
            Text(
                text = stringResource(R.string.explicacao_remocao_disciplina),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                onClick = { mainViewModel.removerDisciplina(disciplina) },
                colors = ButtonDefaults.buttonColors(Color.Red),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Remover",
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { mainViewModel.fecharConfirmacaoRemocaoDisciplinaDialog() },
                colors = ButtonDefaults.buttonColors(Color.White),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Voltar",
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
                contentDescription = stringResource(R.string.remover_disciplina),
                tint = Color.Black
            )
        }
    )
}
