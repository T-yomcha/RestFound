package com.example.restfoundkt.maps


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import android.content.Context
import com.google.android.gms.maps.model.LatLngBounds

@Composable
fun mapsScreen(
    state: MapState,
    setupClusterManager: (Context, GoogleMap)->ZoneClusterManager,
    calculateZoneViewCenter:()->LatLngBounds,
    navController: NavController
){

}
