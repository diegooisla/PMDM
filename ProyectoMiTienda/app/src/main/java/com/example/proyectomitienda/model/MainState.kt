package com.example.proyectomitienda.model

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainState {
    val retrofitApi = RetrofitApi()

    suspend fun fetchLoginToken(username: String, password: String): String? = withContext(Dispatchers.IO) {
        try {
            val request = LoginRequest(username, password)
            val response = retrofitApi.retrofitService.login(request)
            if (response.isSuccessful) {
                response.body()?.accessToken
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("mi-tag", "Error en login", e)
            null
        }
    }

    suspend fun fetchProductos(token: String): List<ProductDto> = withContext(Dispatchers.IO) {
        try {
            val response = retrofitApi.retrofitService.getProducts("Bearer $token")
            if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
        } catch (e: Exception) {
            Log.e("mi-tag", "Error fetchProductos", e)
            emptyList()
        }
    }

    suspend fun fetchCategorias(token: String): List<CategoryDto> = withContext(Dispatchers.IO) {
        try {
            val response = retrofitApi.retrofitService.getCategories("Bearer $token")
            if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
        } catch (e: Exception) {
            Log.e("mi-tag", "Error fetchCategorias", e)
            emptyList()
        }
    }

    suspend fun fetchProductosPorCategoria(token: String, categoryId: Int): List<ProductDto> = withContext(Dispatchers.IO) {
        try {
            val response = retrofitApi.retrofitService.getProductsByCategory("Bearer $token", categoryId)
            if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
        } catch (e: Exception) {
            Log.e("mi-tag", "Error fetchProductosPorCategoria", e)
            emptyList()
        }
    }
}
