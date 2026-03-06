package com.example.recyclerviewcolors.model

import retrofit2.Response
import retrofit2.http.*

interface DogsApiService {
    @GET("{breed}/images")
    suspend fun getDogsImages(@Path ("breed") breed : String):
            Response<DogsResponse>
}