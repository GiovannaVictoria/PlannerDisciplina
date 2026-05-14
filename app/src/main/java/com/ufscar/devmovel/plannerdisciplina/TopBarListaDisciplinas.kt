package com.ufscar.devmovel.plannerdisciplina

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarListaDisciplinas(
    mainViewModel: MainViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF01037A))
            .padding(8.dp),
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.ola),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.White),
                    contentPadding = PaddingValues(10.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.menu),
                        color = Color(0xFF01037A),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            },
            actions = {
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                    tooltip = { PlainTooltip { Text(text = stringResource(R.string.adicionar_card)) } },
                    state = rememberTooltipState()
                ) {
                    IconButton(
                        onClick = { mainViewModel.adicionarDisciplina() },
                        modifier = Modifier
                            .border(1.dp, Color.Blue, CircleShape)
                            .background(Color.White, CircleShape)
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = stringResource(R.string.adicionar_card),
                            tint = Color(0xFF01037A)
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(Color(0xFF01037A))
        )
    }
}
