package com.example.calculadoracomposesimple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculadoracomposesimple.ui.theme.CalculadoraComposeSimpleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraComposeSimpleTheme {
               MiFuncionComposeable()
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_3)
@Composable
fun Preview() {
    MiFuncionComposeable()
}

@Composable
fun MiFuncionComposeable() {
    val viewModel : com.example.calculadoracomposesimple.viewModel.ViewModel = viewModel()

    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    val resultado = viewModel.resultado.collectAsState().value

    Box(modifier = Modifier
        .padding(0.dp, 50.dp)
        .fillMaxWidth()
        .height(400.dp)) {
        Column(modifier = Modifier.align(Alignment.TopCenter)){
            Row(modifier = Modifier.padding(15.dp, 10.dp, 15.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically){
                Text("Número 1: ", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                TextField(
                    value = num1,
                    onValueChange = {
                            nuevoNum -> num1 = nuevoNum
                    },
                    label = {Text("Introduce un número")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Row(modifier = Modifier.padding(15.dp, 10.dp, 15.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically){
                Text("Numero 2: ", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                TextField(
                    value = num2,
                    onValueChange = {
                            nuevoNum -> num2 = nuevoNum
                    },
                    label = {Text("Introduce un número")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
        Row(modifier = Modifier.align(Alignment.Center)) {
            Button(onClick = {viewModel.sumar(num1.toDoubleOrNull() ?: 0.0, num2.toDoubleOrNull() ?: 0.0)}) {
                Text("+")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Button(onClick = {viewModel.restar(num1.toDoubleOrNull() ?: 0.0, num2.toDoubleOrNull() ?: 0.0)}) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Button(onClick = {viewModel.multiplicar(num1.toDoubleOrNull() ?: 0.0, num2.toDoubleOrNull() ?: 0.0)}) {
                Text("*")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Button(onClick = {viewModel.dividir(num1.toDoubleOrNull() ?: 0.0, num2.toDoubleOrNull() ?: 0.0)}) {
                Text("/")
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(
                text = "$resultado",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}