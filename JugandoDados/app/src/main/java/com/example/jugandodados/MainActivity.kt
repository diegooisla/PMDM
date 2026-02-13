package com.example.jugandodados

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var numSaldo : TextView
    lateinit var btnParimpar : MaterialButton
    lateinit var btnMayorMenor : MaterialButton
    lateinit var spinner: Spinner
    lateinit var numApuesta : TextInputEditText
    lateinit var btnLanzarDados : Button

    lateinit var coordinator : CoordinatorLayout
    lateinit var corrutina : Job

    lateinit var txtDado1 : TextView
    lateinit var txtDado2 : TextView

    lateinit var gifDados : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        numSaldo = findViewById<TextView>(R.id.numSaldo)
        numSaldo.text = "100"
        numApuesta = findViewById<TextInputEditText>(R.id.apuesta)

        spinner = findViewById<Spinner>(R.id.spinner)
        spinner.isEnabled = false

        btnParimpar = findViewById<MaterialButton>(R.id.btnParImpar)
        btnMayorMenor = findViewById<MaterialButton>(R.id.btnMayorMenor)
        btnLanzarDados = findViewById<Button>(R.id.btnLanzarDados)

        gifDados = findViewById<ImageView>(R.id.gifDados)
        gifDados.visibility = View.INVISIBLE
        txtDado1 = findViewById<TextView>(R.id.dado1)
        txtDado2 = findViewById<TextView>(R.id.dado2)

        coordinator = findViewById<CoordinatorLayout>(R.id.coordinator)

    }

    //Creo que no puedo añadir el evento onclick dentro del entorno gráfico porque son MaterialButtons
    fun opcionesParesImpares(view: View){
        btnParimpar.setBackgroundColor(Color.parseColor("#8BC34AAF"))
        btnMayorMenor.setBackgroundColor(Color.WHITE)
        rellenarSpinner(listOf<String>("Par", "Impar"))
    }

    fun opcionesMayorMenor(view: View){
        btnMayorMenor.setBackgroundColor(Color.parseColor("#8BC34AAF"))
        btnParimpar.setBackgroundColor(Color.WHITE)
        rellenarSpinner(listOf<String>("Mayor que 7", "Menor que 7"))
    }

    private fun rellenarSpinner(listOf: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.isEnabled = true
    }

    fun lanzarDados(view: View){
        if(!validaciones()){return}

        corrutina = lifecycleScope.launch {
            var saldo = (numSaldo.text.toString()).toInt()
            val apuesta = numApuesta.text.toString().toInt()
            val op = spinner.selectedItem.toString()
            val dado1 = Random.nextInt(1, 7)
            val dado2 = Random.nextInt(1, 7)
            val suma = dado1 + dado2;

            gifDados.visibility = View.VISIBLE
            Glide.with(view).load(R.drawable.dado_imagen_animada_0092).into(gifDados)
            delay(3000)


            txtDado1.text = dado1.toString()
            txtDado2.text = dado2.toString()


            delay(1000)

            when(op) {
                "Par" -> {
                    if(suma%2 == 0){
                        saldo = ganado(saldo, apuesta)
                    } else {
                        saldo = perdido(saldo, apuesta)
                    }
                }
                "Impar" -> {
                    if(suma%2 != 0){
                        saldo = ganado(saldo, apuesta)
                    } else {
                        saldo = perdido(saldo, apuesta)
                    }
                }
                "Mayor que 7" -> {
                    if(suma >= 7){
                        saldo = ganado(saldo, apuesta)
                    } else {
                        saldo = perdido(saldo, apuesta)
                    }
                }
                "Menor que 7" -> {
                    if(suma <= 7){
                        saldo = ganado(saldo, apuesta)
                    } else {
                        saldo = perdido(saldo, apuesta)
                    }
                }
            }


            if (saldo <= 0) {
                gifDados.setImageResource(R.drawable.bancarrota)
                numSaldo.text = "0";

                AlertDialog.Builder(this@MainActivity).setTitle("Fin de partida")
                    .setMessage("Te has quedado a 0")
                    .setPositiveButton("Salir", DialogInterface.OnClickListener{
                        dialog, which ->
                        dialog.dismiss()
                        finish()
                    }).show()

            }else{
                numSaldo.text = saldo.toString();

                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Responde...")
                    .setMessage("¿Deseas continuar?")
                    .setCancelable(false)
                    .setPositiveButton("Si", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        numApuesta.setText("")
                        gifDados.visibility = View.INVISIBLE
                        txtDado1.text = ""
                        txtDado2.text = ""
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        finish()
                    }).show()
            }

        }
    }

    fun ganado(saldo : Int, apuesta : Int) : Int{
        gifDados.setImageResource(R.drawable.ganar_dados)

        return saldo + apuesta
    }

    fun perdido(saldo : Int, apuesta : Int) : Int{
        gifDados.setImageResource(R.drawable.perder_dados)

        return saldo - apuesta
    }

    fun validaciones() : Boolean {
        val saldo = (numSaldo.text.toString()).toInt()
        val apuesta = numApuesta.text.toString()

        if(!spinner.isEnabled) {
            lanzarSnackbar("Debe seleccionar un tipo de apuesta")
            return false
        }

        if(apuesta.isEmpty()){
            lanzarSnackbar("Debe introducir una apuesta mínima")
            return false
        }else if(apuesta.toInt() <= 0){
            lanzarSnackbar("No puede realizar una apuesta negativa o 0")
            return false
        }else if(apuesta.toInt() > saldo){
            lanzarSnackbar("No tiene saldo suficiente")
            return false
        }else
            return true
    }

    fun lanzarSnackbar(cadena : String){
        Snackbar.make(coordinator, cadena, Snackbar.LENGTH_SHORT)
            .show()
    }
}