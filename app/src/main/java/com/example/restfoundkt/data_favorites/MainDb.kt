package com.example.restfoundkt.data_favorites

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(
    entities = [
        NameEntity::class
    ],
    version = 1
)
abstract class MainDb:RoomDatabase() {
    abstract val dao: Dao
    companion object{
        fun createDataBase(context: Context):MainDb{
            return Room.databaseBuilder(
                context,
                MainDb::class.java,
                "test.dp"
            ).build()
        }
    }
}