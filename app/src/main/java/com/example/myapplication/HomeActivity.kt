package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.navArgument
import com.example.myapplication.ui.screen.DaftarProdukScreen
import com.example.myapplication.ui.screen.DetailProductScreen
import com.example.myapplication.ui.screen.HubungiKamiScreen
import com.example.myapplication.ui.viewmodel.ProductViewModel
import com.example.myapplication.ui.theme.JualanTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JualanTheme {
                val navController = rememberNavController()
                val productViewModel: ProductViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = PRODUCT_LIST_ROUTE
                ) {
                    composable(PRODUCT_LIST_ROUTE) {
                        DaftarProdukScreen(
                            navController = navController,
                            onContactUsClick = {
                                navController.navigate(CONTACT_FORM_ROUTE)
                            },
                            viewModel = productViewModel
                        )
                    }
                    composable(
                        route = "$DETAIL_PRODUCT_ROUTE/{productId}",
                        arguments = listOf(
                            navArgument("productId") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        DetailProductScreen(
                            navController = navController,
                            productId = backStackEntry.arguments?.getInt("productId") ?: 0,
                            viewModel = productViewModel
                        )
                    }
                    composable(CONTACT_FORM_ROUTE) {
                        HubungiKamiScreen(navController = navController)
                    }
                }
            }
        }
    }

    private companion object {
        const val PRODUCT_LIST_ROUTE = "daftar_produk"
        const val DETAIL_PRODUCT_ROUTE = "detail"
        const val CONTACT_FORM_ROUTE = "hubungi_kami"
    }
}
