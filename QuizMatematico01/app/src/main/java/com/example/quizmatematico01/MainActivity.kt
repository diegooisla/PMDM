package com.example.quizmatematico01

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.properties.Delegates
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var numAleatorio: TextView
    lateinit var  btnSi : RadioButton
    lateinit var  btnNo : RadioButton

    lateinit var resultado : TextView

    var aleatorio by Delegates.notNull<Int>()

    lateinit var btnFondo : Switch

    lateinit var main : ConstraintLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val colorYellow = ContextCompat.getColor(this, R.color.yellow)
        val colorWhite = ContextCompat.getColor(this, R.color.white)

        main = findViewById<ConstraintLayout>(R.id.main)
        btnFondo = findViewById<Switch>(R.id.switchFondo)

        btnFondo.setOnCheckedChangeListener { btnView, isChecked ->
            if(isChecked){
                main.setBackgroundColor(colorYellow)
            }else
                main.setBackgroundColor(colorWhite)
        }

        numAleatorio = findViewById<TextView>(R.id.numAleatorio)
        btnSi = findViewById<RadioButton>(R.id.btnSi)
        btnNo = findViewById<RadioButton>(R.id.btnNo)
        resultado = findViewById<TextView>(R.id.resultado)



    }

    fun obtenerNum(v: View){
        aleatorio = Random.nextInt(1000, 2001)
        numAleatorio.text = aleatorio.toString()
    }

    fun comprobarResultado(v: View){

        if((btnSi.isChecked && esBisiesto(aleatorio) || (btnNo.isChecked && !esBisiesto(aleatorio)))){
                resultado.setTextColor(Color.GREEN)
                resultado.text = "Correcto"
        }else if ((btnSi.isChecked && !esBisiesto(aleatorio) || (btnNo.isChecked && esBisiesto(aleatorio)))){
                resultado.setTextColor(Color.RED)
                resultado.text = "Error"
        }else{
            resultado.setTextColor(Color.BLUE)
            resultado.text = "Debe escoger una de las dos opciones"
        }



    }

    private fun esBisiesto(year: Int) : Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}