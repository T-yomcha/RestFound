package com.example.restfoundkt.maps

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.model.polygonOptions
import android.content.Context
import javax.inject.Inject
import com.example.restfoundkt.maps.ZoneClusterItem
import com.example.restfoundkt.maps.ZoneClusterManager
import com.example.restfoundkt.maps.calculateCameraViewPoints
import com.example.restfoundkt.maps.getCenterOfPolygon
import com.google.android.gms.maps.model.LatLngBounds


class MapViewModel @Inject constructor() : ViewModel() {

    companion object {
        val POLYGON_FILL_COLOR = Color.parseColor("#ABF44336")
    }
    val state:MutableState<MapState> = mutableStateOf(
        MapState(
            lastKnownLocation =null,
            clusterItems = listOf(
                ZoneClusterItem(
                    id="zone-1",
                    title = "Zone 1",
                    snippet ="This is zone 1",
                    polygonOptions = polygonOptions {
                        add(LatLng(49.105, -122.524))
                        add(LatLng(4.101, -122.529))
                        add(LatLng(49.092, -122.501))
                        add(LatLng(49.1, -122.506))
                        fillColor(POLYGON_FILL_COLOR)
                    }
                )
            )
        )
    )
    @SuppressLint("MissingPermission")
    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    state.value = state.value.copy(
                        lastKnownLocation = task.result,
                    )
                }
            }
        } catch (e: SecurityException) {
            // Show error or something
        }
    }

    fun setupClusterManager(
        context:Context,
        map: GoogleMap,
    ):ZoneClusterManager{
        val clusterManager=ZoneClusterManager(context, map)
        clusterManager.addItems(state.value.clusterItems)
        return clusterManager
    }

    fun calculateZoneLatLngBounds():LatLngBounds{
        val latLngs=state.value.clusterItems.map{it.polygonOptions}
            .map{it.points.map{LatLng(it.latitude, it.longitude)}}.flatten()
        return latLngs.calculateCameraViewPoints().getCenterOfPolygon()
    }

}