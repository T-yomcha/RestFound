package com.example.restfoundkt.maps

import android.location.Location
import com.example.restfoundkt.maps.ZoneClusterItem

data class MapState(
    val lastKnownLocation: Location?,
    val clusterItems: List<ZoneClusterItem>,
)
