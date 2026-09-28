package com.example.firstapp.screens.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firstapp.R
import com.example.firstapp.navigation.ROUTE_LOGIN
import com.example.firstapp.navigation.ROUTE_ONBOARDING
import com.example.firstapp.ui.theme.PaleGreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay

@Composable
fun Splashscreen(navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(2000) //2 second delay
        navController.navigate(ROUTE_ONBOARDING)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF23F13)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.ruby),
            contentDescription = "Logo",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
        Text("Opera App",
            color= PaleGreen,
            fontSize = 32.sp
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    Splashscreen(rememberNavController())
}
