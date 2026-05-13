package com.example.proyectomitienda.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.proyectomitienda.model.CategoryDto
import com.example.proyectomitienda.model.ProductDto
import com.example.proyectomitienda.viewmodel.MyViewModel

private const val BASE_IMAGE_URL = "http://10.0.2.2:8000/images/products/"

@Composable
fun ProductosScreen(myViewModel: MyViewModel) {
    val productos = myViewModel.productos.collectAsState().value
    val categorias = myViewModel.categorias.collectAsState().value
    val categoriaSeleccionada = myViewModel.categoriaSeleccionada.collectAsState().value
    val hayMas = myViewModel.hayMas.collectAsState().value

    LaunchedEffect(Unit) {
        myViewModel.fetchCategorias()
        myViewModel.fetchProductos()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        FiltroCategoriasRow(categorias, categoriaSeleccionada) { categoria ->
            myViewModel.seleccionarCategoria(categoria)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(productos) { producto ->
                ProductoCard(producto)
            }
            if (hayMas) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = { myViewModel.cargarMas() },
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text("Cargar más")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FiltroCategoriasRow(
    categorias: List<CategoryDto>,
    seleccionada: CategoryDto?,
    onSeleccionar: (CategoryDto?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = seleccionada == null,
            onClick = { onSeleccionar(null) },
            label = { Text("Todos") }
        )
        categorias.forEach { categoria ->
            FilterChip(
                selected = seleccionada?.id == categoria.id,
                onClick = { onSeleccionar(categoria) },
                label = { Text(categoria.name) }
            )
        }
    }
}

@Composable
fun ProductoCard(producto: ProductDto) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (producto.img != null) {
                AsyncImage(
                    model = "$BASE_IMAGE_URL${producto.img}",
                    contentDescription = producto.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier.size(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🍃", fontSize = 32.sp)
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = producto.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (producto.discount > 0) {
                    val precioConDescuento = producto.price * (1 - producto.discount / 100.0)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "%.2f €".format(producto.price),
                            fontSize = 13.sp,
                            textDecoration = TextDecoration.LineThrough,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "%.2f €".format(precioConDescuento),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.error
                        )
                        AssistChip(
                            onClick = {},
                            label = { Text("-${producto.discount}%", fontSize = 11.sp) }
                        )
                    }
                } else {
                    Text(
                        text = "%.2f €".format(producto.price),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = "Stock: ${producto.stock}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
