package com.example.restfoundkt


import ContactForm
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.restfoundkt.favorites.roomScreen
import com.example.restfoundkt.maps.MapViewModel
import com.example.restfoundkt.maps.StartMap
import com.example.restfoundkt.maps.mapsScreen

import com.example.restfoundkt.navigation.NavigationGraph
import com.example.restfoundkt.navigation.Screens
import com.example.restfoundkt.realtime_database.ContactList
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