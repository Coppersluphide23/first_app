package com.example.firstapp.models
import com.example.firstapp.R

data class OnboardingItem(
    val title: String,
    val description: String,
    val imageRes: Int
)
val OnboardingItems = listOf(
    OnboardingItem(
        title = "welcome to safari app",
        description = "this app is for safari lovers",
        imageRes = R.drawable.flowers1
    ),
    OnboardingItem(
        title = "OOMF",
        description = "",
        imageRes = R.drawable.flowers2
    ),
    OnboardingItem(
        title = "HHH",
        description = "",
        imageRes = R.drawable.flowers3
    ),


)