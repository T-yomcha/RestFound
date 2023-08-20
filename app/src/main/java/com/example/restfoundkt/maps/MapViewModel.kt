package com.example.restfoundkt.maps

import android.graphics.Color
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.model.polygonOptions
import javax.inject.Inject

class MapViewModel @Inject constructor() : ViewModel() {

    companion object {
        val POLYGON_FILL_COLOR = Color.parseColor("#ABF44336")
    }
    val state:MutableState<MapState> = mutableStateOf(
        MapState(
            lastKnownLocation =null,
            clusterItem = listOf(
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

}