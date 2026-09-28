package com.example.firstapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstapp.screens.login.LoginScreen
import com.example.firstapp.screens.register.RegisterScreen
import com.example.firstapp.screens.splashscreen.Splashscreen
import com.example.firstapp.screens.dashboard.DashboardScreen
import com.example.firstapp.screens.onboardingscreen.OnboardingScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH,
){
  NavHost(
      modifier = modifier,
      navController = navController,
      startDestination = startDestination
  ){
     composable (ROUTE_SPLASH) {
         Splashscreen(navController)
     }
     composable (ROUTE_LOGIN) {
         LoginScreen(navController)
     }
     composable (ROUTE_REGISTER) {
         RegisterScreen(navController)
     }
     composable (ROUTE_DASHBOARD)  {
         DashboardScreen(navController)
     }
     composable (ROUTE_ONBOARDING) {
         OnboardingScreen(navController)
     }
  }

}