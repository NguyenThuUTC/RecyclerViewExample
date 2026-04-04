package com.example.recyclerviewex.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recyclerviewex.data.local.dao.MovieDao
import com.example.recyclerviewex.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class, ], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun movieDao() : MovieDao
}