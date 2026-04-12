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
            )
                .addMigrations(MIGRATION_1_2)
                .addMigrations(MIGRATION_2_3)
                .addMigrations(MIGRATION_3_4)
                .build().also { INSTANCE = it }
        }
    }
}