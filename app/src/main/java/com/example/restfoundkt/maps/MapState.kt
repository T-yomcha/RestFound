package com.example.restfoundkt.maps

import android.location.Location

data class MapState(
    val lastKnownLocation: Location?,
    val clusterItem: List<ZoneClusterItem>,
)
