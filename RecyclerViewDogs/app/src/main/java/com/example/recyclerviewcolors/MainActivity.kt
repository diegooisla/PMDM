package com.example.recyclerviewcolors

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolors.databinding.ActivityMainBinding
import com.example.recyclerviewcolors.model.Datos
import com.example.recyclerviewcolors.model.DogsResponse
import com.example.recyclerviewcolors.recycler.MyAdapter
import com.example.recyclerviewcolors.viewModel.DogsViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myViewModel : DogsViewModel by viewModels()
    private lateinit var myAdapter: MyAdapter
    private lateinit var misDatos : Datos
    private lateinit var miLayaoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        miLayaoutManager = LinearLayoutManager(this@MainActivity)
        binding.recycler.layoutManager = miLayaoutManager


        /*myViewModel.data.observe(this@MainActivity) {
            when(it.status){
                "success" -> {
                    myAdapter = MyAdapter(it)
                    binding.recycler.adapter = myAdapter
                }
                "error" -> Toast.makeText(this@MainActivity, "No hay fotos de esa raza",
                        Toast.LENGTH_SHORT).show()
            }

            binding.recycler.layoutManager = LinearLayoutManager(this@MainActivity)

            val mydividerItemDecoration = DividerItemDecoration(
                this@MainActivity, DividerItemDecoration.VERTICAL
            )
            binding.recycler.addItemDecoration(mydividerItemDecoration)
        }*/

        myViewModel.dataScroll.observe(this@MainActivity){
            when(it.status){
                "success" -> {
                    if(it.paginaActual == 1){
                        misDatos = it
                        myAdapter = MyAdapter(DogsResponse(it.status, it.mensaje))

                        binding.recycler.adapter = myAdapter
                    }else{
                        myAdapter.notifyItemRangeInserted(it.paginaActual!!*10, it.mensaje!!.size)
                    }
                }
                "error" -> Toast.makeText(this@MainActivity, "No hay fotos de esa raza", Toast.LENGTH_SHORT).show()
            }

        }

        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled( recyclerView: RecyclerView,  dx : Int, dy: Int){
                super.onScrolled(recyclerView, dx, dy)
                var finalScroll = false

                if(miLayaoutManager.findLastVisibleItemPosition()%10 >= 9 &&
                    miLayaoutManager.findLastVisibleItemPosition()/10 == (misDatos.paginaActual!!-1)){
                    finalScroll = true
                }

                if(finalScroll && misDatos.paginaActual!! < misDatos.numPaginas!!){
                    Snackbar.make(binding.root, "Si desea recuperar más fotos pulse:",
                        Snackbar.LENGTH_LONG)
                        .setAction("Cargar más", {myViewModel.scrollFotos()}).show()
                }
            }
        })
    }

    fun recoverImages(view : View) {
        if (binding.txtDog.text.toString() == "")
            Toast.makeText(this@MainActivity, "Debe escribir algo previamente", Toast.LENGTH_SHORT)
                .show()
        else {
            val breed = binding.txtDog.text.toString().trim().lowercase()
            myViewModel.recuperarFotosPaginacion(breed)

        }

    }
}