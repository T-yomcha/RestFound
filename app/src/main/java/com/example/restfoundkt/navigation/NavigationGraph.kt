package com.example.restfoundkt.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restfoundkt.maps.MapViewModel
import com.example.restfoundkt.maps.mapsScreen
import com.example.restfoundkt.presentation.login_screen.signInScreen
import com.example.restfoundkt.presentation.signup_screen.signUpScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels

@AndroidEntryPoint
class NavigationGraph(private val viewModel: MapViewModel) : ComponentActivity() {

    @Composable
    fun navigationGraph() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screens.SignUpScreen.route
        ) {
            composable(route = Screens.SignInScreen.route) {
                signInScreen(viewModel = hiltViewModel(), navController = navController)
            }
            composable(route = Screens.SignUpScreen.route) {
                signUpScreen(viewModel = hiltViewModel(), navController = navController)
            }
            composable(route = Screens.MapsScreen.route) {
                mapsScreen(
                    state = viewModel.state.value,
                    setupClusterManager = viewModel::setupClusterManager,
                    calculateZoneViewCenter = viewModel::calculateZoneLatLngBounds,
                    viewModel= hiltViewModel(),
                    navController=navController
                )
            }
        }
    }
}
