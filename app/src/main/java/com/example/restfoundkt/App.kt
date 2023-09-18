package com.example.restfoundkt;

import android.app.Application
import com.example.restfoundkt.data_favorites.MainDb
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    val database by lazy { MainDb.createDataBase(this) }
}
