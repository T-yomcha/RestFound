package com.example.restfoundkt.data_favorites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class NameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val restName: String
)
