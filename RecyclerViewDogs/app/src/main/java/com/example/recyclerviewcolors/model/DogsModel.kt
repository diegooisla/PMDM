package com.example.recyclerviewcolors.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogsModel {
    val retrofitApi = RetrofitApi()
    lateinit var fotosPerroCargado : DogsResponse
    lateinit var misDatos : Datos
    suspend fun recoverImages(breed : String): DogsResponse = withContext(Dispatchers.IO){
        val response = retrofitApi.retrofitService.getDogsImages(breed)

        if(response.isSuccessful)
            DogsResponse(response.body()!!.status, response.body()!!.message)
        else
            DogsResponse("error", null)

    }

    suspend fun recuperarFotosPaginacion(breed : String): Datos = withContext(Dispatchers.IO){

        val respuesta = retrofitApi.retrofitService.getDogsImages(breed)
        if (respuesta.isSuccessful){
            fotosPerroCargado = DogsResponse(respuesta.body()!!.status, respuesta.body()!!.message)

            if (fotosPerroCargado.message!!.size > 0){
                var numPaginas = fotosPerroCargado.message!!.size/10

                if(fotosPerroCargado.message!!.size%10 != 0) numPaginas++
                misDatos = Datos(fotosPerroCargado.status, numPaginas, 1, mutableListOf())

                var rango = Math.min(fotosPerroCargado.message!!.size -1, 9)
                for(i in 0..rango){
                    misDatos.mensaje!!.add(fotosPerroCargado.message!!.get(i))
                }
            }
            misDatos!!
        }else {
            Datos("error", null, null, null)!!
        }
    }

    suspend fun scrollFotos() : Datos{
        var inicio : Int
        var fin : Int

        inicio = misDatos.paginaActual!! * 10
        misDatos.paginaActual = misDatos.paginaActual!! +1
        if(misDatos.paginaActual!! < misDatos.numPaginas!!){
            fin = (misDatos.paginaActual!! *10 - 1)
        }else{
            fin = (fotosPerroCargado.message!!.size - 1)
        }

        for (i in inicio..fin){
            misDatos.mensaje!!.add(fotosPerroCargado.message!!.get(i))
        }

        return misDatos
    }


}