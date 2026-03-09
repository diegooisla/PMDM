package com.example.recyclerviewcolors.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewcolors.model.DogsModel
import com.example.recyclerviewcolors.model.DogsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogsViewModel : ViewModel() {
     var myModel =  DogsModel()
    private var _data = MutableStateFlow(DogsResponse("", mutableListOf()))
    val data : StateFlow<DogsResponse> get() = _data

    fun recoverImages(breed: String) {
        viewModelScope.launch {
            _data.value = myModel.recoverImages(breed)
        }
    }


}