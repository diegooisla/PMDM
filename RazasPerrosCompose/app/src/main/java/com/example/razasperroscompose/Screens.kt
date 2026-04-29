package com.example.razasperroscompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.razasperroscompose.viewModel.MiViewModel

@Composable
fun BreedList(navController: NavController, myViewModel : MiViewModel) {
    val breeds = myViewModel.breeds.collectAsState().value

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF3EEF7))) {
        Text(
            text = "Lista de Razas",
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 25.dp, 0.dp, 25.dp),
            textAlign = TextAlign.Center
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(breeds) { index, breed ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("dog_detail/${breed}")
                        }
                        .padding(0.dp, 25.dp, 0.dp, 25.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = breed,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
fun DogDetail(breed: String, subbreed: String? = null, navController: NavController, myViewModel: MiViewModel) {

    myViewModel.obtainDogsDetails(breed)


    val dogDetail = myViewModel.dogs.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 25.dp, 0.dp, 25.dp)
    ) {
        Text(
            text = "Fotos de $breed",
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 25.dp, 0.dp, 25.dp),
            textAlign = TextAlign.Center
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(dogDetail?.message ?: emptyList()) { image ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp, 0.dp, 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = image,
                        contentDescription = "Foto de perro $breed",
                        modifier = Modifier.size(180.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(text = "Volver")
            }
        }
    }
}