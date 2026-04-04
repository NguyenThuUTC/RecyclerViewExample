package com.example.recyclerviewex.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recyclerviewex.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query(
        """
        SELECT * FROM movie WHERE viewedAt IS NOT NULL ORDER BY viewedAt DESC
    """
    )
    fun getMovies(): Flow<List<MovieEntity>>
}