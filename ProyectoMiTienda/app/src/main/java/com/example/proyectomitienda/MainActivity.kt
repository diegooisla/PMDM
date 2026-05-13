package com.example.proyectomitienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectomitienda.ui.theme.ProyectoMiTiendaTheme
import com.example.proyectomitienda.viewmodel.MyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myViewModel: MyViewModel = viewModel()

            ProyectoMiTiendaTheme {
                Navegation(myViewModel)
            }
        }
    }
}
