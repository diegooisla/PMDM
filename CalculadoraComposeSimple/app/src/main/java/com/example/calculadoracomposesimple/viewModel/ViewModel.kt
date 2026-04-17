package com.example.calculadoracomposesimple.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadoracomposesimple.model.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    val myState = MainState()

    private val _resultado = MutableStateFlow<Double>(0.0)

    val resultado : StateFlow<Double> get() = _resultado

    fun sumar(num1: Double, num2: Double) {
        viewModelScope.launch {
            _resultado.value = myState.sumar(num1, num2)
        }
    }

    fun restar(num1: Double, num2: Double) {
        viewModelScope.launch {
            _resultado.value = myState.restar(num1, num2)
        }
    }

    fun multiplicar(num1: Double, num2: Double) {
        viewModelScope.launch {
            _resultado.value = myState.multiplicar(num1, num2)
        }
    }

    fun dividir(num1: Double, num2: Double) {
        viewModelScope.launch {
            _resultado.value = myState.dividir(num1, num2)
        }
    }

}