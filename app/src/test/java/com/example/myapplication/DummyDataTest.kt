package com.example.myapplication

import com.example.myapplication.data.dummy.DummyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DummyDataTest {
    @Test
    fun dummyData_hasThreeCategoriesAndFifteenProducts() {
        assertEquals(3, DummyData.categories.size)
        assertEquals(15, DummyData.products.size)
    }

    @Test
    fun everyCategory_hasFiveProducts() {
        DummyData.categories.forEach { category ->
            val productsInCategory = DummyData.products.count {
                it.category_id == category.id
            }
            assertEquals(category.products_count, productsInCategory)
        }
    }

    @Test
    fun everyProduct_referencesItsCategory() {
        assertTrue(
            DummyData.products.all { product ->
                product.category?.id == product.category_id
            }
        )
    }
}
