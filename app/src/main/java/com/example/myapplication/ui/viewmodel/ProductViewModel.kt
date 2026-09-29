package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Category
import com.example.myapplication.data.model.Product
import com.example.myapplication.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ProductUiState {
    data object Loading : ProductUiState
    data class Success(
        val categories: List<Category>,
        val products: List<Product>
    ) : ProductUiState
    data class Error(val message: String) : ProductUiState
}

class ProductViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val categories = ApiClient.instance.getCategories()
                val products = ApiClient.instance.getProducts().map { product ->
                    product.copy(
                        category = categories.find { category ->
                            category.id == product.category_id
                        }
                    )
                }
                _uiState.value = ProductUiState.Success(categories, products)
            } catch (exception: Exception) {
                _uiState.value = ProductUiState.Error(
                    exception.message ?: "Gagal memuat data produk"
                )
            }
        }
    }
}
