package com.example.recyclerviewex.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewex.R
import com.example.recyclerviewex.data.local.database.AppDatabaseProvider
import com.example.recyclerviewex.data.repository.MovieRepository
import com.example.recyclerviewex.databinding.FragmentFavoriteMovieBinding
import com.example.recyclerviewex.ui.common.BaseCreateFactoryViewModel
import com.example.recyclerviewex.ui.movies.MovieAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FavoriteMovieFragment : Fragment() {

    private var binding: FragmentFavoriteMovieBinding? = null

    private val db by lazy {
        AppDatabaseProvider.getInstance(requireContext().applicationContext)
    }

    private val repository by lazy {
        MovieRepository(db.movieDao())
    }

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels {
        BaseCreateFactoryViewModel {
            FavoriteMovieViewModel(repository)
        }
    }

    private var movieAdapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
    }

    private fun initViews() {
        movieAdapter = MovieAdapter(
            onMovieClick = { movie ->
                findNavController().navigate(
                    R.id.movieDetailFragment,
                    Bundle().apply {
                        putInt("movieId", movie.id ?: -1)
                    }
                )
            },
            onFavoriteClick = { movie ->
                favoriteMovieViewModel.removeFavorite(movie)
            }
        )

        binding?.run {
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            rcvFavorites.adapter = movieAdapter
            rcvFavorites.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun observeData() {
        favoriteMovieViewModel.favoriteMovies.onEach { movies ->
            movieAdapter?.submitList(movies)
            binding?.txtEmpty?.visibility = if (movies.isEmpty()) View.VISIBLE else View.GONE
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}
