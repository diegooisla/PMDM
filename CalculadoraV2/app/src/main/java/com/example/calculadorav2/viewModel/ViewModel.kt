package com.example.calculadorav2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadorav2.model.Datos
import com.example.calculadorav2.model.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private var _datos = MutableStateFlow(Datos("", "", ""))
    val datos: StateFlow<Datos> get() = _datos
    var myState = MainState()

    fun acumularNum(num : String){
        viewModelScope.launch {
            _datos.value = myState.acumularNum(num)
        }
    }

    fun acumularSimb(simb : String){
        viewModelScope.launch {
            _datos.value = myState.acumularSimbolo(simb)
        }
    }
}