package com.example.recyclerviewex.data.local.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {

    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS movie_genre_cross_ref (
                movieId INTEGER NOT NULL,
                genreId INTEGER NOT NULL,
                PRIMARY KEY(movieId, genreId)
            )
        """)


    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {

    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE movie ADD COLUMN releaseDate TEXT")


    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {

    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE movie ADD COLUMN isFavorite INTEGER")
    }
}
