package com.example.quizmatematico02

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.properties.Delegates
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var numAleatorio: TextView
    lateinit var check2 : CheckBox
    lateinit var check3 : CheckBox
    lateinit var check5 : CheckBox
    lateinit var check10 : CheckBox
    lateinit var checkNone : CheckBox
    lateinit var resultado : TextView

    lateinit var okView : ImageView
    lateinit var koView: ImageView
    var aleatorio by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        check2 = findViewById<CheckBox>(R.id.check2)
        check3 = findViewById<CheckBox>(R.id.check3)
        check5 = findViewById<CheckBox>(R.id.check5)
        check10 = findViewById<CheckBox>(R.id.check10)
        checkNone = findViewById<CheckBox>(R.id.checkNone)
        numAleatorio = findViewById<TextView>(R.id.numAleatorio)
        resultado = findViewById<TextView>(R.id.resultado)
        okView = findViewById<ImageView>(R.id.okView)
        koView = findViewById<ImageView>(R.id.koView)


    }

    fun obtenerNum(v: View){
        aleatorio = Random.nextInt(1900, 2501)
        numAleatorio.text = aleatorio.toString()
    }

    fun comprobarResultado(v: View){
        val div2 = aleatorio % 2 == 0
        val div3 = aleatorio % 3 == 0
        val div5 = aleatorio % 5 == 0
        val div10 = aleatorio % 10 == 0
        val divNone = !(div2 || div3 || div5 || div10)

        val checked2 = check2.isChecked
        val checked3 = check3.isChecked
        val checked5 = check5.isChecked
        val checked10 = check10.isChecked
        val noneChecked = checkNone.isChecked

        if(!(checked2 || checked3 || checked5 || checked10 || noneChecked)){
            resultado.text = "Debe escoger una opción mínimo"
            okView.visibility = View.GONE
            koView.visibility = View.GONE
        }else{
            if(checked2 == div2 &&
                checked3 == div3 &&
                checked5 == div5 &&
                checked10 == div10 &&
                noneChecked == divNone){
                resultado.text = "Correcto"
                okView.visibility = View.VISIBLE
                koView.visibility = View.GONE
            }else{
                resultado.text = "Error"
                okView.visibility = View.GONE
                koView.visibility = View.VISIBLE
            }
        }


    }
}