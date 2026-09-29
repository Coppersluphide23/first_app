package com.example.firstapp.ViewModel
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.firstapp.models.User
import com.example.firstapp.navigation.ROUTE_DASHBOARD
import com.example.firstapp.navigation.ROUTE_LOGIN
import com.example.firstapp.navigation.ROUTE_REGISTER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel ( var navController: NavHostController, var context : Context){
       var mAuth = FirebaseAuth.getInstance()
    //register function to create new users
    fun signup(fullName:String,email:String,password:String,confirmPassword:String){
        //validation
        if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            Toast.makeText(context,"Email and Password can't be blank",Toast.LENGTH_LONG).show()
            return
        }else if(password != confirmPassword){
            Toast.makeText(context,"Password and Confirm Password do not match",Toast.LENGTH_LONG).show()
        }else{
            //create user
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                if(it.isSuccessful){
                    val userdata= User(fullName,email,password,mAuth.currentUser!!.uid)
                    //save user data to real-time database
                    val regRef= FirebaseDatabase.getInstance().getReference().child("Users/"+mAuth.currentUser!!.uid)
                    regRef.setValue(userdata).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(context,"Registration Successful",Toast.LENGTH_LONG).show()
                            //navigate to login
                            navController.navigate(ROUTE_LOGIN)
                        }else{
                            Toast.makeText(context,"${it.exception!!.message})",Toast.LENGTH_LONG).show()
                            navController.navigate(ROUTE_REGISTER)
                        }
                    }
                }else{
                    navController.navigate(ROUTE_REGISTER)
                }

            }

        }

    }
    //login function
    fun login(email : String, password : String){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
            if(it.isSuccessful){
                Toast.makeText(context,"Login Successful",Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_DASHBOARD)
            }else{
                Toast.makeText(context,it.exception?.message?:"error logging in",Toast.LENGTH_LONG).show()
            }
        }
    }
    //signout function
    fun signout(){
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
        { popUpTo(0) }
    }
    //getting current username
    fun getCurrentUsername(onResult:(String) -> Unit){
        val userId = mAuth.currentUser?.uid
        if (userId==null){
            onResult("user")
            return
        }
        FirebaseDatabase.getInstance().getReference("Users")
            .child(userId)
            .get()
            .addOnSuccessListener {snapshot ->
                val fullName=snapshot.child("fullName").getValue(String::class.java)
                onResult(fullName ?: "user")
            }


    }
}