package com.example.calculadoracomposesimple.model

class MainState {
    suspend fun sumar(num1 : Double, num2: Double): Double {
        return num1 + num2
    }
    suspend fun restar(num1 : Double, num2: Double): Double {
        return num1 - num2
    }
    suspend fun multiplicar(num1 : Double, num2: Double): Double {
        return num1 * num2
    }
    suspend fun dividir(num1 : Double, num2: Double): Double {
        return num1 / num2
    }

}