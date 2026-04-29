package com.example.razasperroscompose.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.razasperroscompose.model.DogsImageResponse
import com.example.razasperroscompose.model.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MiViewModel : ViewModel() {
    val myState : MainState = MainState()

    private val _breeds = MutableStateFlow<List<String>>(emptyList())
    val breeds: StateFlow<List<String>> get() = _breeds

    private val _dogs = MutableStateFlow<DogsImageResponse?>(null)
    val dogs: StateFlow<DogsImageResponse?> get() = _dogs

    fun obtainBreedList(){
        Log.e("mi-tag", "Estoy en el viewModel")
        viewModelScope.launch {
            _breeds.value = myState.fetchBreeds()
        }
    }

    init {
        obtainBreedList()
    }

    fun obtainDogsDetails(breed: String, subbreed: String? = null) {
        Log.e("mi-tag", "Estoy en el viewModel")
        viewModelScope.launch {
            _dogs.value = myState.recoverImages(breed, subbreed)
        }
    }



}