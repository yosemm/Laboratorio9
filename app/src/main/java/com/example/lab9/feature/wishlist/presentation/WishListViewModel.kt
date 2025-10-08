package com.example.lab9.feature.wishlist.presentation

import androidx.lifecycle.ViewModel
import com.example.lab9.feature.wishlist.domain.model.Product
import com.example.lab9.feature.wishlist.domain.model.WishlistUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WishlistViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        val sampleProducts = listOf(
            Product(1, "Laptop Gamer", false),
            Product(2, "Audífonos Bluetooth", false),
            Product(3, "Teclado Mecánico", false),
            Product(4, "Mouse Inalámbrico", false),
            Product(5, "Monitor 4K", false),
            Product(6, "Webcam HD", false),
            Product(7, "Micrófono USB", false),
            Product(8, "Silla Ergonómica", false),
            Product(9, "Escritorio Ajustable", false),
            Product(10, "Lámpara LED", false)
        )

        _uiState.update { currentState ->
            currentState.copy(products = sampleProducts)
        }
    }

    fun toggleWishlist(productId: Int) {
        _uiState.update { currentState ->
            val updatedProducts = currentState.products.map { product ->
                if (product.id == productId) {
                    product.copy(isWishlisted = !product.isWishlisted)
                } else {
                    product
                }
            }
            currentState.copy(products = updatedProducts)
        }
    }
}
