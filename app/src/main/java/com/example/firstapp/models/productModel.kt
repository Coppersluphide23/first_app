package com.example.firstapp.models

import com.cloudinary.Url

data class Product(
    var id: String="",
    var name: String="",
    var description: String="",
    var price: String="",
    var imageUrl: String=""
)
