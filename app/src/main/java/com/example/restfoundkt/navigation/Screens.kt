package com.example.restfoundkt.navigation

sealed class Screens(val route: String) {
    object SignInScreen : Screens(route = "SignIn_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")
    object MapsScreen : Screens(route = "Maps_Screen")
    object RoomScreen : Screens(route = "Room_Screen")
    object AddDataScreen : Screens(route = "Add_Data_Screen")
    object ReadDataScreen : Screens(route = "Read_Data_Screen")
}