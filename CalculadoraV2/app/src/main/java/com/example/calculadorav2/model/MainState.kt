package com.example.calculadorav2.model

class MainState {

    var misDatos : Datos = Datos("", "", "0")
    var num1 : Double = 0.0
    var ultSimbo : Boolean = false

    fun acumularNum(num : String) : Datos{
        if(misDatos.calcularResultado){
            limpiar(num)
            return misDatos

        }else if(num == "Clear"){
            limpiar("0")
            return misDatos

        }else{
            if(misDatos.input == "0") misDatos.input = num
            else  misDatos.input += num

            ultSimbo = false
            misDatos.mensaje = ""
            return misDatos
        }


    }

    private fun limpiar(num: String) {
        num1 = 0.0
        ultSimbo = false
        misDatos = Datos("", "", "")
    }

    fun acumularSimbolo(simb : String) : Datos {
        misDatos.calcularResultado = false

        if(ultSimbo && simb != "=")
            misDatos.mensaje = "No es posible realizar eso"
        else{
            if(simb != "="){
                num1 = misDatos.input.toDouble()
                misDatos.acumulado += misDatos.input + simb
                ultSimbo = true
                misDatos.input = "0";
            }else{
                var operacion = misDatos.acumulado.last()
                var resultado = 0.0
                when(operacion){
                    '+' -> resultado = num1 + misDatos.input.toDouble()
                    '-' -> resultado = num1 - misDatos.input.toDouble()
                    '*' -> resultado = num1 * misDatos.input.toDouble()
                    '/' -> resultado = num1 / misDatos.input.toDouble()

                }

                misDatos.acumulado += misDatos.input + simb
                misDatos.input = resultado.toString()
                misDatos.calcularResultado = true
            }

        }


        return misDatos
    }


}