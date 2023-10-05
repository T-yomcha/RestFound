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
import dagger.hilt.android.lifecycle.HiltViewModel


@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {

    val state: MutableState<MapState> = mutableStateOf(
        MapState(
            lastKnownLocation = null,
            clusterItems = listOf(
                ZoneClusterItem(
                    id = "zone-1",
                    title = "Вкусно и точка",
                    snippet = "улица Покрышкина, 2, Москва, 119602",
                    polygonOptions = polygonOptions {
                        add(LatLng(55.664100, 37.48095))
                        add(LatLng(55.664296, 37.480988))
                        add(LatLng(55.664253, 37.481353))
                        add(LatLng(55.664045, 37.481217))
                        fillColor(POLYGON_FILL_COLOR_FAST_FOOD)
                    }
                ),
                ZoneClusterItem(
                    id = "zone-2",
                    title = "Kfc",
                    snippet = "улица Покрышкина, 2к1, Москва, 119602",
                    polygonOptions = polygonOptions {
                        add(LatLng(55.664093, 37.482488))
                        add(LatLng(55.664289, 37.482198))
                        add(LatLng(55.664030, 37.481907))
                        add(LatLng(55.663987, 37.482398))
                        fillColor(POLYGON_FILL_COLOR_FAST_FOOD)
                    }
                ),
                ZoneClusterItem(
                    id = "zone-3",
                    title = "Вкусно и точка, Kfc, Farш, The Бык...",
                    snippet = "проспект Вернадского, 86А, Москва, 119571",
                    polygonOptions = polygonOptions {
                        add(LatLng(55.662462, 37.481038))
                        add(LatLng(55.662680, 37.480072))
                        add(LatLng(55.663625, 37.480443))
                        add(LatLng(55.663455, 37.481676))
                        fillColor(POLYGON_FILL_COLOR_MULTI)
                    }
                ),
                ZoneClusterItem(
                    id = "zone-4",
                    title = "Burger king",
                    snippet = "проспект Вернадского, 86Бс1, Москва, 119571",
                    polygonOptions = polygonOptions {
                        add(LatLng(55.662129, 37.480132))
                        add(LatLng(55.662387, 37.480031))
                        add(LatLng(55.662337, 37.480483))
                        add(LatLng(55.662147, 37.480517))
                        fillColor(POLYGON_FILL_COLOR_FAST_FOOD)
                    }
                ),
                ZoneClusterItem(
                    id = "zone-5",
                    title = "Мука, Торпеда, Хачапури, Баркас, Вафли...",
                    snippet = "проспект Вернадского, 86Б, Москва, 119571",
                    polygonOptions = polygonOptions {
                        add(LatLng(55.661353, 37.479759))
                        add(LatLng(55.661848, 37.479898))
                        add(LatLng(55.661798, 37.480449))
                        add(LatLng(55.661299, 37.480317))
                        fillColor(POLYGON_FILL_COLOR_GREEN_FOOD)
                    }
                ),
                ZoneClusterItem(
                    id = "zone-6",
                    title = "Prime, Хлебница, Kfc...",
                    snippet = "улица Покрышкина, 4, Москва, 119602",
                    polygonOptions = polygonOptions {
                        add(LatLng(55.664837, 37.481224))
                        add(LatLng(55.665432, 37.481196))
                        add(LatLng(55.665177, 37.481719))
                        add(LatLng(55.664899, 37.481978))
                        fillColor(POLYGON_FILL_COLOR_MULTI)
                    }
                )
            )
        )
    )

    companion object {
        private val POLYGON_FILL_COLOR_FAST_FOOD = 0xFFFF7673.toInt()
        private val POLYGON_FILL_COLOR_MULTI = 0xFFE366B5.toInt()
        private val POLYGON_FILL_COLOR_GREEN_FOOD = 0xFF67E667.toInt()
    }















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
        context: Context,
        map: GoogleMap,
    ): ZoneClusterManager {
        val clusterManager = ZoneClusterManager(context, map)
        clusterManager.addItems(state.value.clusterItems)
        return clusterManager
    }

    fun calculateZoneLatLngBounds(): LatLngBounds {
        // Get all the points from all the polygons and calculate the camera view that will show them all.
        val latLngs = state.value.clusterItems.map { it.polygonOptions }
            .map { it.points.map { LatLng(it.latitude, it.longitude) } }.flatten()
        return latLngs.calculateCameraViewPoints().getCenterOfPolygon()
    }

}