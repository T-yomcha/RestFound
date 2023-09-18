package com.example.restfoundkt.favorites

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.restfoundkt.App
import com.example.restfoundkt.data_favorites.MainDb
import com.example.restfoundkt.data_favorites.NameEntity
import kotlinx.coroutines.launch

class FavouritesViewModel(val database: MainDb) : ViewModel() {
    val itemsList = database.dao.getAllItems()
    val newRestName = mutableStateOf("")
    var nameEntity: NameEntity? = null

    fun insertItem() = viewModelScope.launch {
        val nameItem = nameEntity?.copy(restName = newRestName.value)
            ?: NameEntity(restName = newRestName.value)
        database.dao.insertItem(nameItem)
        nameEntity = null
        newRestName.value = ""
    }

    fun deleteItem(item: NameEntity) = viewModelScope.launch {
        database.dao.deleteItem(item)
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return FavouritesViewModel(database) as T
            }
        }
    }
}