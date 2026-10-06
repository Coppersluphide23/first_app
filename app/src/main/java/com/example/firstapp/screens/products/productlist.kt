package com.example.firstapp.screens.products

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.example.firstapp.navigation.ROUTE_ADDPRODUCT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun productListScreen(navController: NavHostController){
    //add a scaffold
    Scaffold(
    // Add a topbar
    topBar = {
        CenterAlignedTopAppBar(title = {Text("Product List", color = Color(0xFFC0EDAD), fontSize = 30.sp)},

            colors = topAppBarColors(
                containerColor = Color(0xFFEDADE0),
                titleContentColor = Color(0xFF994A3C)
            )
        )
    },
    floatingActionButton = {
        FloatingActionButton(
            onClick = {navController.navigate(ROUTE_ADDPRODUCT)}
        ){
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                tint = Color(0xFF107BE0)
            )
        }
    }

){ paddingValues ->

    }



}
@Preview(showBackground = true)
@Composable
fun productListPreview(){
    productListScreen(rememberNavController())
}