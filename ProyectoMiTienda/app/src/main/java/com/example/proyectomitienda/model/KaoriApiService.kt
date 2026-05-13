package com.example.proyectomitienda.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface KaoriApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("products")
    suspend fun getProducts(@Header("Authorization") token: String): Response<List<ProductDto>>

    @GET("categories")
    suspend fun getCategories(@Header("Authorization") token: String): Response<List<CategoryDto>>

    @GET("categories/{id}/products")
    suspend fun getProductsByCategory(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<List<ProductDto>>
}
