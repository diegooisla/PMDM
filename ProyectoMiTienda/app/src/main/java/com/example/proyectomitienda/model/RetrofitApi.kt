package com.example.proyectomitienda.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class RetrofitApi {
    val retrofitBase: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: KaoriApiService = retrofitBase.create(KaoriApiService::class.java)
}
