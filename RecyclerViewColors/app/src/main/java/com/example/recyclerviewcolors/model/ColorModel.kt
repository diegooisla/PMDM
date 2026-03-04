package com.example.recyclerviewcolors.model

class ColorModel {
    val colors = mutableListOf<MyColor>(
        MyColor("Rojo Carmesí", "#DC143C"),
        MyColor("Azul Marino", "#000080"),
        MyColor("Verde Esmeralda", "#50C878"),
        MyColor("Amarillo Mostaza", "#FFDB58"),
        MyColor("Morado Violeta", "#8F00FF"),
        MyColor("Naranja Coral", "#FF7F50"),
        MyColor("Rosa Fucsia", "#FF00FF"),
        MyColor("Turquesa", "#40E0D0"),
        MyColor("Gris Antracita", "#303030"),
        MyColor("Beige Arena", "#F5F5DC"),
        MyColor("Azul Acero", "#4682B4"),
        MyColor("Azul Cielo", "#87CEEB"),
        MyColor("Verde Oliva", "#808000"),
        MyColor("Marrón Chocolate", "#7B3F00"),
        MyColor("Lavanda", "#E6E6FA"),
    )

    suspend fun returnList() : Datos {
        return Datos("ok", colors)
    }
}