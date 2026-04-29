package com.example.razasperroscompose.model

import retrofit2.Response
import retrofit2.http.*

interface DogsApiService {
    @GET("breed/{breed}/images")
    suspend fun getDogsImages(@Path ("breed") breed : String):
            Response<DogsImageResponse>

    @GET("breeds/list/all")
    suspend fun getAllBreeds() : Response<BreedsResponse>

    @GET("breed/{breed}/{subbreed}/images")
    suspend fun getSubbreedImages(@Path("breed") breed: String, @Path("subbreed") subbreed: String): Response<DogsImageResponse>

}