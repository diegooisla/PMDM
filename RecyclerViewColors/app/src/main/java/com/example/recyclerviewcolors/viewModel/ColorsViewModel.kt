package com.example.recyclerviewcolors.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewcolors.model.ColorModel
import com.example.recyclerviewcolors.model.Datos
import com.example.recyclerviewcolors.model.MyColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ColorsViewModel : ViewModel() {
     var myModel =  ColorModel()
    private var _datos = MutableStateFlow(Datos("", mutableListOf<MyColor>()))
    val datos : StateFlow<Datos> get() = _datos

    fun returnList() {
        viewModelScope.launch {
            _datos.value = myModel.returnList()
        }
    }


}