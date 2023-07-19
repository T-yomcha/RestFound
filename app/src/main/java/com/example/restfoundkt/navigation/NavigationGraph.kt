package com.example.restfoundkt.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restfoundkt.maps.googleMapView
import com.example.restfoundkt.presentation.login_screen.signInScreen
import com.example.restfoundkt.presentation.signup_screen.SignUpViewModel
import com.example.restfoundkt.presentation.signup_screen.signUpScreen
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.SignUpScreen.route
    ) {
        composable(route = Screens.SignInScreen.route) {
            signInScreen(viewModel = hiltViewModel(), navController=navController)
        }
        composable(route = Screens.SignUpScreen.route) {
            signUpScreen(viewModel = hiltViewModel(), navController=navController)
        }
        composable(route=Screens.MapsScreen.route){
            googleMapView(navController=navController)
        }
    }
}