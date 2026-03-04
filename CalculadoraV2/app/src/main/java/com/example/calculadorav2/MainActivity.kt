package com.example.calculadorav2

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.calculadorav2.databinding.ActivityMainBinding
import com.example.calculadorav2.viewModel.ViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val myViewModel : ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       /* myViewModel.datos.observe(this){

            if(it.mensaje != ""){
                Snackbar.make(binding.main, it.mensaje, Snackbar.LENGTH_SHORT).show()
            }else{
                binding.txtHistorial.text = it.acumulado
                binding.txtResultado.text = it.input
            }


           *//* binding.txtResultado.text = it.acumulado
            if(it.calcularResultado)
                binding.txtResultado.visibility = View.VISIBLE*//*

        }*/

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                myViewModel.datos.collect {
                    if(it.mensaje != ""){
                        Snackbar.make(binding.main, it.mensaje, Snackbar.LENGTH_SHORT).show()
                    }else{
                        binding.txtHistorial.text = it.acumulado
                        binding.txtResultado.text = it.input
                    }

                }
            }
        }
    }

    fun escribir(view: View){
        val btn = view as Button
        myViewModel.acumularSimb(btn.text.toString())
    }

    fun escribirNum(view: View){
        val btn = view as Button
        myViewModel.acumularNum(btn.text.toString())
    }


}