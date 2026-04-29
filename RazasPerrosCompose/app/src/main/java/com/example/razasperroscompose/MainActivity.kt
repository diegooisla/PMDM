package com.example.razasperroscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.razasperroscompose.ui.theme.RazasPerrosComposeTheme
import com.example.razasperroscompose.viewModel.MiViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myViewModel: MiViewModel = viewModel()

            RazasPerrosComposeTheme {
                    Navegation(myViewModel)
            }
        }
    }
}
