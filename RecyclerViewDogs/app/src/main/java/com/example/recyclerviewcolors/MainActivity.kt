package com.example.recyclerviewcolors

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewcolors.databinding.ActivityMainBinding
import com.example.recyclerviewcolors.recycler.MyAdapter
import com.example.recyclerviewcolors.viewModel.DogsViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myViewModel : DogsViewModel by viewModels()
    private lateinit var myAdapter: MyAdapter

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




        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                myViewModel.data.collect {
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
                }
            }
        }
    }

    fun recoverImages(view : View) {
        if(binding.txtDog.text.toString() == "")
            Toast.makeText(this@MainActivity, "Debe escribir algo previamente", Toast.LENGTH_SHORT).show()
        else{
            myViewModel.recoverImages(binding.txtDog.text.toString())
        }
    }
}