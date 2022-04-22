package com.example.htmlparsetest

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.htmlparsetest.views.*

private const val catalog = "/catalog/dxl/"
private const val keychain = "/catalog/other/breloki/"
private const val moto = "/catalog/motosignalizacii/"
private const val gps = "/catalog/gpsmayaki/"
private const val accessories = "/catalog/other/"
private const val cart = "cart"


@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {
    val listProduct by mainViewModel.listProduct.observeAsState(emptyList())
    val bottomItems = listOf(catalog, accessories, cart, moto, gps)
    //val cartList by mainViewModel.cart.observeAsState(emptyList())

    val navControllerHost = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigation {
            bottomItems.forEach { point ->
                BottomNavigationItem(
                    selected = false,
                    onClick = {
                        mainViewModel.getData(point = point)
                    },
                    label = {
                        when (point) {
                            catalog -> {
                                Text(text = "Авто")
                            }
                            keychain -> {
                                Text(text = "Брелки")
                            }
                            moto -> {
                                Text(text = "Мото")
                            }
                            gps -> {
                                Text(text = "Маяки")
                            }
                            accessories -> {
                                Text(text = "Аксесуар")
                            }
                            cart -> {
                                Text(text = "Козина")
                            }
                        }
                    },
                    icon = {
                        when (point) {
                            catalog -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_auto),
                                    contentDescription = null
                                )
                            }
                            keychain -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_keychain),
                                    contentDescription = null
                                )
                            }
                            moto -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_moto),
                                    contentDescription = null
                                )
                            }
                            gps -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_gps),
                                    contentDescription = null
                                )
                            }
                            cart -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_shopping_cart),
                                    contentDescription = null
                                )
                            }
                            accessories -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_accessories),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            }
        }
    }) {
        NavHost(
            navController = navControllerHost,
            startDestination = catalog,
            modifier = Modifier.padding(it)
        ) {
            composable(catalog) {
                ProductList(
                    productsList = listProduct,
                    navController,
                    mainViewModel
                )
            }
            composable(keychain) {
                ProductList(
                    productsList = listProduct,
                    navController,
                    mainViewModel
                )
            }
            composable(moto) {
                ProductList(
                    productsList = listProduct,
                    navController,
                    mainViewModel
                )
            }
            composable(gps) {
                ProductList(
                    productsList = listProduct,
                    navController,
                    mainViewModel
                )
            }
            composable(accessories) {
                ProductList(
                    productsList = listProduct,
                    navController,
                    mainViewModel
                )
            }
            composable(cart) {
                ProductList(
                    productsList = listProduct,
                    navController,
                    mainViewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductList(
    productsList: List<Product>,
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val link by mainViewModel.point.observeAsState("")
    val listCart by mainViewModel.cart.observeAsState(emptyList())
    val finalPrise by mainViewModel.finalPrise.observeAsState("")
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    )
    {
        if (link != cart) {
            items(productsList) { product ->
                if (link == accessories) {
                    ItemButton2(
                        mainViewModel = mainViewModel,
                        product = product,
                        link = link
                    )
                } else {
                    ItemButton(
                        product = product,
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                }
            }
        } else {
            stickyHeader {
                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.background)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Subtitle5(text = "Общая сумма заказа:" , modifier = Modifier.weight(1f))
                        Subtitle5(text = finalPrise)
                    }
                }
            }
            items(listCart) { selectProduct ->
                ItemInCard(product = selectProduct)
            }

        }
    }
}

/*fun getPrise(listCart: List<Product>) : String {
    val list = mutableListOf<String>()
    val listPrise = mutableListOf<Int>()
    var int = 0

    listCart.forEach {
        list.add(it.price)
    }

    list.forEach {
        val format = it.replace(" ", "")
        listPrise.add(format.toInt())
    }
    listPrise.forEach {
        int.plus(it)
    }
    return int.toString()
}*/


