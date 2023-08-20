package com.example.restfoundkt.maps

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Instrumentation.ActivityResult
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartMap: ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val viewModel: MapViewModel by viewModels()

    private val requestPermissionLauncher=registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        isGranded: Boolean -> if(isGranded){
            viewModel.getDeviceLocation(fusedLocationProviderClient)
        }
    }

    fun askPermission()=when{
        ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED->{
                    viewModel.getDeviceLocation(fusedLocationProviderClient)
                }
        else ->{
            requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
    }
}