package com.example.recyclerviewex.data.local.database

import android.content.Context
import androidx.room.Room

object AppDatabaseProvider {
    @Volatile
    private var INSTANCE: AppDataBase? = null

    fun getInstance(context: Context): AppDataBase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "movie db"
            ).build().also { INSTANCE = it }
        }
    }
}