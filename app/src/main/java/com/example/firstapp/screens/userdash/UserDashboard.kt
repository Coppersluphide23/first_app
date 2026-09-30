package com.example.firstapp.screens.userdash

import android.R.attr.icon
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashboard(navController: NavHostController){
    //add a scaffold
    Scaffold(
       //add topbar
        topBar = {
            TopAppBar(title = {Text("User Dashboard")},
                colors = topAppBarColors(
                    containerColor = Color(0xFFEDADE0),
                    titleContentColor = Color.Black
                )
            )
        },
        //bottom bar
        bottomBar = {
            NavigationBar(
                containerColor = Color(0XFF95C9B9),
            ){
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Home,
                            contentDescription = "Homeicon")},
                    label = { Text("Home",color= Color(0xFF994A3C))}
                )
                NavigationBarItem(
                    selected =true,
                    onClick = {},
                    icon = {Icon(Icons.Default.Person,
                        contentDescription = "Person icon")},
                    label = { Text("Profile",color= Color(0XFF994A3C))},
                )
                NavigationBarItem(
                    selected =true,
                    onClick = {},
                    icon = {Icon(Icons.Default.ShoppingCart,
                        contentDescription = "Cart icon")},
                    label = { Text("Cart",color= Color(0xFF994A3C))}
                )

            }
        }
    ) {
        paddingValues->
    }
}
@Preview(showBackground = true)
@Composable
fun UserDashboardPreview(){
    UserDashboard(rememberNavController())
}