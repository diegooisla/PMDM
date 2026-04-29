package com.example.razasperroscompose.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class RetrofitApi {
    val retrofitBase: Retrofit = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: DogsApiService = retrofitBase.create(DogsApiService::class.java)
}