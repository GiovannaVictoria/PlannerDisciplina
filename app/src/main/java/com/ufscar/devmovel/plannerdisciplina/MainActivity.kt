package com.ufscar.devmovel.plannerdisciplina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ufscar.devmovel.plannerdisciplina.navigation.App
import com.ufscar.devmovel.plannerdisciplina.ui.theme.PlannerDisciplinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlannerDisciplinaTheme {
                App()
            }
        }
    }
}
