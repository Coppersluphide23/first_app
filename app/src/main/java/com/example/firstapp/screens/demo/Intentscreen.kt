package com.example.firstapp.screens.demo

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun IntentScreen(navController : NavHostController){


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "INTENTS",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.Blue
        )
        val context = LocalContext.current
        //Open camera
        Button(onClick = {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            context.startActivity(intent)
        })
        {
            Text("Open Camera")
        }
        //Open browser
        Button(onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            context.startActivity(intent)
        })
        { Text("Open Browser") }
        //OPEN GOOGLE MAPS
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=your location"))
                context.startActivity(intent)
            })

        { Text("Open Google Maps") }
        //open sms
        Button(
            onClick = {
                val smsintent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:0748742259"))
                smsintent.putExtra("sms_body", "hello from grove street ")
                context.startActivity(smsintent)
            }
        ) { Text("send sms") }
        //open email
        Button(
            onClick = {
                val emailIntent = Intent(
                    Intent.ACTION_SENDTO,
                    Uri.fromParts("mailto", "luthermk2250@gmail.com", null)
                )
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello from firstapp")
                context.startActivity(emailIntent)
            }
        ) {
            Text("send email")
        }
        //open stk push
        Button(onClick = {
            val simToolKitLaunchIntent =
                context.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { context.startActivity(it) }
        }) { Text("stk push") }
        //call a number
        Button(onClick = {
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0748742259"))
            context.startActivity(dialIntent)
        })
        { Text("call") }

    }
}
@Preview(showBackground = true)
@Composable
fun IntentScreenPreview(){
    IntentScreen(rememberNavController())
}