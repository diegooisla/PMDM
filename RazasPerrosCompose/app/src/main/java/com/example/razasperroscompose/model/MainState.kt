package com.example.razasperroscompose.model

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainState {
    val retrofitApi = RetrofitApi()

    suspend fun fetchBreeds(): List<String> = withContext(Dispatchers.IO) {

        try {
            val response = retrofitApi.retrofitService.getAllBreeds()

            if (response.isSuccessful) {
                Log.e("mi-tag", "Ha ido bien")
                response.body()?.message?.keys?.toList() ?: emptyList()
            } else {
                Log.e("mi-tag", "No ha ido bien: ${response.code()}")
                emptyList()
            }

        } catch (e: Exception) {
            Log.e("mi-tag", "Error en fetchBreeds", e)
            Log.e("mi-tag", "Tipo de error: ${e::class.java.name}")
            emptyList()
        }


    }

    suspend fun recoverImages(breed: String, subbreed: String? = null): DogsImageResponse = withContext(Dispatchers.IO) {
        val response = if (subbreed.isNullOrEmpty()) {
            retrofitApi.retrofitService.getDogsImages(breed)
        } else {
            retrofitApi.retrofitService.getSubbreedImages(breed, subbreed)
        }

        if (response.isSuccessful) {
            response.body() ?: DogsImageResponse( emptyList(), "error")
        } else {
            DogsImageResponse( emptyList(), "error")
        }
    }
}