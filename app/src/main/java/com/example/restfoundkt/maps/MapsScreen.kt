package com.example.restfoundkt.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*


@Composable
fun googleMapView(navController: NavController){
    val location=LatLng(1.355, 103.864)
    val infoWindowState= rememberMarkerState(position = location)
    val cameraPositionState= rememberCameraPositionState{
        position= CameraPosition.fromLatLngZoom(location, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState=cameraPositionState
    ){
        Marker(
            state= MarkerState(position = location),
        )
    }
}