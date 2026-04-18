package com.example.recyclerviewex.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.recyclerviewex.data.local.entity.GenreEntity
import com.example.recyclerviewex.data.local.entity.MovieEntity
import com.example.recyclerviewex.data.local.entity.MovieGenreCrossRef
import com.example.recyclerviewex.data.local.model.MovieWithGenreDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenre(genre: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieGenre(movieGenre: List<MovieGenreCrossRef>)


    @Transaction
    @Query(
        """
        SELECT * FROM movie WHERE viewedAt IS NOT NULL ORDER BY viewedAt DESC
    """
    )
    fun getMovies(): Flow<List<MovieWithGenreDetail>>

    @Query(
        """
        SELECT * FROM movie
        WHERE id = :movieId
        LIMIT 1
    """
    )
    suspend fun getMovieById(movieId: Int): MovieEntity?

    @Query(
        """
        SELECT id FROM movie
        WHERE id IN (:movieIds)
        AND isFavorite = 1
    """
    )
    suspend fun getFavoriteMovieIds(movieIds: List<Int>): List<Int>

    @Query(
        """
        UPDATE movie
        SET isFavorite = :isFavorite
        WHERE id = :movieId
    """
    )
    suspend fun updateFavoriteStatus(movieId: Int, isFavorite: Boolean)

    @Query(
        """
        SELECT * FROM movie
        WHERE isFavorite = 1
        ORDER BY viewedAt DESC, originalTitle ASC
    """
    )
    fun getFavoriteMovies(): Flow<List<MovieWithGenreDetail>>
}
