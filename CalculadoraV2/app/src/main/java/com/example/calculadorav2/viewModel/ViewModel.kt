package com.example.calculadorav2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadorav2.model.Datos
import com.example.calculadorav2.model.MainState
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private val _datos = MutableLiveData(Datos("", "", ""))
    val datos: LiveData<Datos> get() = _datos
    val myState = MainState()

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