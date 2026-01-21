package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var num1 : EditText
    lateinit var num2 : EditText

    lateinit var resultado : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        num1 = findViewById<EditText>(R.id.num1)
        num2 = findViewById<EditText>(R.id.num2)
        resultado = findViewById<TextView>(R.id.resultado)
    }

    fun clickSuma(v: View) {
        resultado.text = (num1.text.toString().toInt() + num2.text.toString().toInt()).toString()
    }

    fun clickResta(v: View) {
        resultado.text = (num1.text.toString().toInt() - num2.text.toString().toInt()).toString()
    }

    fun clickMultiplicacion(v: View) {
        resultado.text = (num1.text.toString().toInt() * num2.text.toString().toInt()).toString()
    }

    fun clickDivision(v: View) {
        if(num2.text.toString() != "0")
            resultado.text = (num1.text.toString().toDouble() / num2.text.toString().toDouble()).toString()
        else
            resultado.text = "imposible"
    }


}

