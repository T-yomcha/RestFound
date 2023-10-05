package com.example.restfoundkt


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.restfoundkt.favorites.roomScreen
import com.example.restfoundkt.maps.MapViewModel
import com.example.restfoundkt.maps.StartMap
import com.example.restfoundkt.maps.mapsScreen

import com.example.restfoundkt.navigation.NavigationGraph
import com.example.restfoundkt.navigation.Screens
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranded: Boolean ->
        if (isGranded) {
            viewModel.getDeviceLocation(fusedLocationProviderClient)
        }
    }

    fun askPermission() = when {
        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED -> {
            viewModel.getDeviceLocation(fusedLocationProviderClient)
        }
        else -> {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val viewModel: MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        askPermission()
        setContent {
            NavigationGraph(viewModel = viewModel).navigationGraph()
        }
    }
}