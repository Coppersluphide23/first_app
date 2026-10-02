package com.example.firstapp.screens.products

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun updateProductScreen(navController: NavHostController){

}
@Preview(showBackground = true)
@Composable
fun updateProductPreview(){
    updateProductScreen(rememberNavController())
}
