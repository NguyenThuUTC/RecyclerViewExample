package com.example.recyclerviewex.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recyclerviewex.data.local.dao.MovieDao
import com.example.recyclerviewex.data.local.entity.GenreEntity
import com.example.recyclerviewex.data.local.entity.MovieEntity
import com.example.recyclerviewex.data.local.entity.MovieGenreCrossRef

@Database(entities = [
    MovieEntity::class,
    GenreEntity::class,
    MovieGenreCrossRef::class
                     ], version = 3)
abstract class AppDataBase: RoomDatabase() {
    abstract fun movieDao() : MovieDao
}