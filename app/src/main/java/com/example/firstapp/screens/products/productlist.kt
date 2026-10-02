package com.example.firstapp.screens.products

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun productListScreen(navController: NavHostController){
    //add a scaffold
    Scaffold(
    // Add a topbar
    topBar = {
        TopAppBar(title = {Text("Product List", color = Color(0xFFC0EDAD), fontSize = 20.sp)},

            colors = topAppBarColors(
                containerColor = Color(0xFFEDADE0),
                titleContentColor = Color(0xFF994A3C)
            )
        )
    },
){ paddingValues ->

    }



}
@Preview(showBackground = true)
@Composable
fun productListPreview(){
    productListScreen(rememberNavController())
}