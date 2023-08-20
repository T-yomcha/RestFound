package com.example.restfoundkt.maps


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun googleMapView(navController: NavController){
    val singapore = LatLng(55.45, 37.36)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = rememberMarkerState(position = singapore),
            title = "Moscow",
            snippet = "Marker in Center",
            draggable = true,
            icon= BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)
        )
    }
}
