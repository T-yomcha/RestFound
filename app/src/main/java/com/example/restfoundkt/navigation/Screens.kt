package com.example.restfoundkt.navigation

sealed class Screens(val route: String) {
    object SignInScreen : Screens(route = "SignIn_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")
    object MapsScreen : Screens(route = "Maps_Screen")


}