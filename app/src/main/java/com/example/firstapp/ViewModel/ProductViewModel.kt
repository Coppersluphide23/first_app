package com.example.firstapp.ViewModel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.firstapp.navigation.ROUTE_PRODUCTLIST
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.InputStream

class ProductViewModel (var navController: NavHostController, val context: Context) {
    var cloudinaryUrl = "https://api.cloudinary.com/v1_1/gb3zm0vd/upload"
    var uploadPreset = "products"
    val databasereference = FirebaseDatabase.getInstance().getReference("products")

    //functions
    //crud-create,read,update & delete
    //upload product to firebase realtime database
    fun addProduct(name: String, price: String, description: String, imageUri: Uri?) {
        val ref = databasereference.push()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val imageUrl = if (imageUri != null) {
                    uploadToCloudinary(context = context, uri = imageUri)
                } else {
                    ""
                }
                //product data to be stored in realtime db
                val productData = mapOf(
                    "id" to ref.key,
                    "name" to name,
                    "price" to price,
                    "description" to description,
                    "userId" to userId,
                    "imageUrl" to imageUrl
                )
                ref.setValue(productData).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(context,"Product added successfully", Toast.LENGTH_LONG).show()
                    //navigate to product list
                    navController.navigate(ROUTE_PRODUCTLIST)

                  }
                    else{
                        Toast.makeText(context,"${it.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context, "Upload failed ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //upload image to cloudinary function
    private fun uploadToCloudinary(context: Context, uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileBytes = inputStream?.use { it.readBytes() }
            ?: throw Exception("Image read failed")
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                "image.jpg",
                RequestBody.create("image/*".toMediaTypeOrNull(), fileBytes)
            )
            .addFormDataPart("upload_preset", uploadPreset)
            .build()
        val request = Request.Builder()
            .url(cloudinaryUrl)
            .post(requestBody)
            .build()
        val response = OkHttpClient().newCall(request).execute()
        if (!response.isSuccessful) throw Exception("Image upload failed")
        val responseBody = response.body?.string() ?: ""
        val secureUrl = JSONObject(responseBody).optString("secure_url").takeIf { it.isNotBlank() }
        return requireNotNull(secureUrl) { "Failed to get image url" }
    }
}
