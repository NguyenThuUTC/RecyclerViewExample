package com.example.recyclerviewex.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewex.data.remote.ServiceProvider
import com.example.recyclerviewex.data.repository.MovieRepository
import com.example.recyclerviewex.mapper.mapToMovieDetailUiModel
import com.example.recyclerviewex.mapper.toGenreEntity
import com.example.recyclerviewex.mapper.toMovieEntity
import com.example.recyclerviewex.mapper.toMovieGenreCross
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Task 3.1: Tạo ViewModel [MovieDetailViewModel]
 */
class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {


    /**
     * Task 3.2: tạo data model dành cho màn hình detail [MovieDetailUiModel]
     *
     * Task 3.3: [_movieDetail]: MutableLiveData dùng trong ViewModel để cập nhật dữ liệu
     *          movieDetail: LiveData public để Fragment observe và nhận dữ liệu thay đổi
     */
    private val _movieDetail = MutableLiveData<MovieDetailUiModel>()
    val movieDetail: LiveData<MovieDetailUiModel> = _movieDetail

    val service = ServiceProvider.api


    /**
     * Task 4: tạo api để
     *  get movie detail [com.example.recyclerviewex.data.remote.ApiService.getMovieDetail]
     *  get credits [com.example.recyclerviewex.data.remote.ApiService.getCredits]
     *
     */



    fun getMovieDetail(movieId: Int) {

        /**
         * Task 5: call api
         */
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val movieDeffer = async {
                    service.getMovieDetail(
                        apiKey = "e5ab9840aeaee234fbaefbbdb86adaae",
                        movieId = movieId
                    )
                }
                val creditsDeffer = async {
                    service.getCredits(
                        apiKey = "e5ab9840aeaee234fbaefbbdb86adaae",
                        movieId = movieId
                    )
                }
                val movieResponse = movieDeffer.await()
                val creditsResponse = creditsDeffer.await()

                val movieData = movieResponse.takeIf { it.isSuccessful }?.body()
                val creditsData = creditsResponse.takeIf { it.isSuccessful }?.body()


                movieData?.let {
                    movieRepository.saveViewedMovie(it.toMovieEntity(System.currentTimeMillis()))
                    movieData.genres?.let {

                    }

                    movieRepository.insertGenre(
                        it.genres.toGenreEntity() ?: emptyList(),
                        it.genres.toMovieGenreCross(it.id ?: 0) ?: emptyList()
                    )
                }

                /**
                 * Task 6: Mapping dữ liệu cho UI [mapToMovieDetailUiModel]
                 */

                _movieDetail.postValue(
                    mapToMovieDetailUiModel(
                        movie = movieData,
                        credits = creditsData
                    )
                )
            } catch (e: Exception) {
                Log.e("Error", "${e.message}")
            }
        }
    }
}
