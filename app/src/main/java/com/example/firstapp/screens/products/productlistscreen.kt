package com.example.firstapp.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firstapp.models.Product
import com.example.firstapp.navigation.ROUTE_ADDPRODUCT
import com.example.firstapp.R

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

){ innerPadding ->
        val products = listOf(
            Product("1","bag","a nice bag","5900", R.drawable.bag.toString()),
            Product("2","shoes","nice shoes","5000", R.drawable.shoes.toString()),
            Product("3","watch","nice watch","15000", R.drawable.watch.toString()),
            Product("4","laptop","Asus laptop","100000", R.drawable.laptop.toString()),
        )
        //lazy column
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(4.dp)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            items(products){ item ->
                // product card
                Card(
                    shape= RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp,
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(bottom=16.dp),
                ){
                    //product image
                    Image(painter = painterResource(id=item.imageURL.toInt()),
                    contentDescription ="product image",
                    contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                    )
                    //column
                    Column(modifier = Modifier.padding(16.dp))
                    {
                        Text(text = item.name,
                            color=Color.Black,
                            fontSize = 28.sp)
                        Text(
                            text=item.description,
                            fontSize = 24.sp,
                            color = Color(0xFF182138)
                        )
                        Text(
                            text=item.price,
                            fontSize = 24.sp,
                            color = Color(0xFFC23A3A)
                        )
                    }
                    //row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Button(onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFF24030)
                                ),
                                modifier = Modifier.weight(2f)
                            ){
                                Text(text="Delete")
                            }
                            Button(onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF72CF81)
                                ),
                                modifier = Modifier.weight(2f)
                                ){
                                Text(text="Update", fontWeight = FontWeight.Bold)
                            }

                    }

                }

            }
        }
    }



}
@Preview(showBackground = true)
@Composable
fun productListPreview(){
    productListScreen(rememberNavController())
}