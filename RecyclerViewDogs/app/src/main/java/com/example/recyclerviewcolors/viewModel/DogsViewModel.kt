package com.example.recyclerviewcolors.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewcolors.model.Datos
import com.example.recyclerviewcolors.model.DogsModel
import com.example.recyclerviewcolors.model.DogsResponse
import kotlinx.coroutines.launch

class DogsViewModel : ViewModel() {
     var myModel =  DogsModel()
    private var _data = MutableLiveData(DogsResponse("", mutableListOf()))
    private var _dataScroll = MutableLiveData(Datos(null.toString(), null, null, ArrayList()))
    val data : LiveData<DogsResponse> get() = _data
    val dataScroll : LiveData<Datos> get() = _dataScroll

    fun recoverImages(breed: String) {
        viewModelScope.launch {
            _data.value = myModel.recoverImages(breed)
        }
    }

    fun recuperarFotosPaginacion(breed : String){
        viewModelScope.launch {
            _dataScroll.value = myModel.recuperarFotosPaginacion(breed)
        }
    }

    fun scrollFotos(){
        viewModelScope.launch {
            _dataScroll.value = myModel.scrollFotos()
        }
    }


}