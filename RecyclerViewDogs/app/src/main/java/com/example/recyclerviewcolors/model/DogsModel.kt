package com.example.recyclerviewcolors.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogsModel {
    val retrofitApi = RetrofitApi()

    suspend fun recoverImages(breed : String): DogsResponse = withContext(Dispatchers.IO){
        val response = retrofitApi.retrofitService.getDogsImages(breed)

        if(response.isSuccessful)
            DogsResponse(response.body()!!.status, response.body()!!.message)
        else
            DogsResponse("error", null)

    }
}