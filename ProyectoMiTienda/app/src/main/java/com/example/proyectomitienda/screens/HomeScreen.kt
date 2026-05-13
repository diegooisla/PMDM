package com.example.proyectomitienda.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

private const val SHOP_URL = "http://10.0.2.2:8080"

@Composable
fun HomeScreen() {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun onReceivedError(
                        view: WebView,
                        errorCode: Int,
                        description: String,
                        failingUrl: String
                    ) {
                        view.loadData(
                            "<h2>No se puede conectar a la tienda</h2><p>Comprueba que el servidor web está en marcha.</p>",
                            "text/html",
                            "UTF-8"
                        )
                    }
                }
                settings.javaScriptEnabled = true
                loadUrl(SHOP_URL)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
