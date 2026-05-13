package com.example.proyectomitienda.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectomitienda.model.CategoryDto
import com.example.proyectomitienda.model.MainState
import com.example.proyectomitienda.model.ProductDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    val myState: MainState = MainState()
    private val PAGE_SIZE = 10

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> get() = _token

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> get() = _username

    private val _loginError = MutableStateFlow(false)
    val loginError: StateFlow<Boolean> get() = _loginError

    private val _categorias = MutableStateFlow<List<CategoryDto>>(emptyList())
    val categorias: StateFlow<List<CategoryDto>> get() = _categorias

    private val _todosLosProductos = MutableStateFlow<List<ProductDto>>(emptyList())

    private val _productos = MutableStateFlow<List<ProductDto>>(emptyList())
    val productos: StateFlow<List<ProductDto>> get() = _productos

    private val _hayMas = MutableStateFlow(false)
    val hayMas: StateFlow<Boolean> get() = _hayMas

    private val _categoriaSeleccionada = MutableStateFlow<CategoryDto?>(null)
    val categoriaSeleccionada: StateFlow<CategoryDto?> get() = _categoriaSeleccionada

    fun login(usuario: String, contrasena: String) {
        viewModelScope.launch {
            val result = myState.fetchLoginToken(usuario, contrasena)
            if (result != null) {
                _token.value = result
                _username.value = usuario
                _loginError.value = false
            } else {
                _loginError.value = true
            }
        }
    }

    fun logout() {
        _token.value = null
        _username.value = ""
        _loginError.value = false
        _categorias.value = emptyList()
        _productos.value = emptyList()
        _todosLosProductos.value = emptyList()
        _categoriaSeleccionada.value = null
        _hayMas.value = false
    }

    fun fetchCategorias() {
        val currentToken = _token.value ?: return
        viewModelScope.launch {
            _categorias.value = myState.fetchCategorias(currentToken)
        }
    }

    fun fetchProductos() {
        val currentToken = _token.value ?: return
        viewModelScope.launch {
            val result = myState.fetchProductos(currentToken)
            _todosLosProductos.value = result
            _productos.value = result.take(PAGE_SIZE)
            _hayMas.value = result.size > PAGE_SIZE
        }
    }

    fun seleccionarCategoria(categoria: CategoryDto?) {
        _categoriaSeleccionada.value = categoria
        val currentToken = _token.value ?: return
        viewModelScope.launch {
            val result = if (categoria == null) {
                myState.fetchProductos(currentToken)
            } else {
                myState.fetchProductosPorCategoria(currentToken, categoria.id)
            }
            _todosLosProductos.value = result
            _productos.value = result.take(PAGE_SIZE)
            _hayMas.value = result.size > PAGE_SIZE
        }
    }

    fun cargarMas() {
        val actuales = _productos.value
        val todos = _todosLosProductos.value
        val nuevos = todos.take(actuales.size + PAGE_SIZE)
        _productos.value = nuevos
        _hayMas.value = nuevos.size < todos.size
    }
}
