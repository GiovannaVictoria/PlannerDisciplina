package com.ufscar.devmovel.plannerdisciplina.ui.components.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ufscar.devmovel.plannerdisciplina.R

@Composable
fun BottomBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0XFF4e4f4f))
    ) {
        BottomAppBar(
            containerColor = Color(0XFF4e4f4f),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(R.drawable.icone_copyright),
                    contentDescription = stringResource(R.string.copyright),
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(4.dp)
                )
                Text(
                    text = stringResource(R.string.giovanna_victoria_rossetto),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}
