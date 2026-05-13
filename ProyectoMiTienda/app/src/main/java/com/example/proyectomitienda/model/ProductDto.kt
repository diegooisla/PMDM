package com.example.proyectomitienda.model

data class ProductDto(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val discount: Int,
    val img: String?,
    val stock: Int
)
