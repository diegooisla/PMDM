package com.example.razasperroscompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.razasperroscompose.viewModel.MiViewModel

@Composable
fun Navegation(myViewModel: MiViewModel) {
    val myNavController = rememberNavController()
    NavHost(myNavController, startDestination = "Initial") {
        composable("Initial") {BreedList(myNavController, myViewModel)}

        composable("dog_detail/{breed}") {
            val raza = it.arguments?.getString("breed") ?: ""
            DogDetail(raza, null, myNavController, myViewModel)
        }
        composable("DogDetail/{breed}/{subbreed}") {
            val raza = it.arguments?.getString("breed") ?: ""
            val subraza = it.arguments?.getString("subbreed")
            DogDetail(raza, subraza, myNavController, myViewModel)
        }
    }
}