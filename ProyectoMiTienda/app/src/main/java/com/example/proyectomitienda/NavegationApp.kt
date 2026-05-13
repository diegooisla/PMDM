package com.example.proyectomitienda

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectomitienda.screens.LoginScreen
import com.example.proyectomitienda.screens.MainScreen
import com.example.proyectomitienda.viewmodel.MyViewModel

@Composable
fun Navegation(myViewModel: MyViewModel) {
    val navController = rememberNavController()
    val token = myViewModel.token.collectAsState().value

    LaunchedEffect(token) {
        if (token != null) {
            navController.navigate("main") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(myViewModel) }
        composable("main") { MainScreen(navController, myViewModel) }
    }
}
