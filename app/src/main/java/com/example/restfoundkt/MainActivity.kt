package com.example.restfoundkt


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.restfoundkt.maps.StartMap

import com.example.restfoundkt.navigation.NavigationGraph
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        StartMap().askPermission()
        setContent {
            NavigationGraph().navigationGraph()
        }
    }
}