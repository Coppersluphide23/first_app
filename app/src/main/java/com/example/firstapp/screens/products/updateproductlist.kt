package com.example.firstapp.screens.products

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.firstapp.R
import com.example.firstapp.ViewModel.ProductViewModel
import com.example.firstapp.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun updateProductScreen(navController: NavHostController,productId:String){
    //add scaffold and top bar
    Scaffold(
        // Add a topbar
        topBar = {
            TopAppBar(title = {Text("Update Product", color = Color(0xFFC0EDAD), fontSize = 20.sp)},

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEDADE0),
                    titleContentColor = Color(0xFF994A3C)
                )
            )
        },
    )
    {
            innerpadding->
        //column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text= "Update Product",fontSize = 20.sp, color = Color(0xFF832B1C))
            Spacer(modifier = Modifier.height(10.dp))
            var productName by remember { mutableStateOf("") }
            var description by remember { mutableStateOf( "") }
            var price by remember { mutableStateOf("") }
            var imageUri by remember {mutableStateOf<Uri?>(null)}
            var imageUrl by remember { mutableStateOf<String?>(null) }
            //image picker
            val imagePickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
            ){ uri : Uri? ->
                if(uri!=null)imageUri = uri
            }
            val context = LocalContext.current
            val myproductViewModel = ProductViewModel(navController, context)
            //fretch product data
            LaunchedEffect(productId){
                myproductViewModel.databasereference.child(productId).get()
                    .addOnSuccessListener { snapshot ->
                        val product = snapshot.getValue(Product::class.java)
                        product ?. let{
                            productName = it.name
                            description = it.description
                            price = it.price
                            imageUrl = it.imageUrl
                        }
                    }
            }

            //Outlined text field
            OutlinedTextField(
                value = productName,
                onValueChange = {productName=it},
                label = { Text("Product Name")},
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description=it },
                label = { Text("Product Description")},
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = price,
                onValueChange = { price=it },
                label = { Text("Product Price")},
                modifier = Modifier.padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(10.dp))
            //image preview (old or new)
            val imageSource :Any? =imageUri ?:imageUri ?:imageUrl
                AsyncImage(
                    model = imageSource,
                    contentDescription = "product",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                )
            Spacer(modifier = Modifier.height(10.dp))
            //image picker button
            Button(
                onClick = {imagePickerLauncher.launch("image/*")},
                               modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF507FE5)))
            {
                Text("Update image", fontSize = 10.sp, color= Color(0xFF994A3C))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    myproductViewModel.updateProduct(
                        productId = productId,
                        name = productName,
                        price = price,
                        description = description,
                        imageUri = imageUri
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE5B650),
                    contentColor = Color(0xFFE56B50))
            ) {
                Text("Update Product", fontSize = 10.sp)
            }



        }
    }

}

@Preview(showBackground = true)
@Composable
fun updateProductPreview(){
    updateProductScreen(rememberNavController(),productId = "2")
}
