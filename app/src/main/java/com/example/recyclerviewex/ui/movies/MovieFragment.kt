package com.example.recyclerviewex.ui.movies

import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewex.databinding.FragmentMovieBinding
import com.example.recyclerviewex.ui.MovieUIModel

class MovieFragment : Fragment() {

    var binding: FragmentMovieBinding? = null
    var movieAdapter: MovieAdapter? = null

    val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel.loadMovies()
        binding?.run {
            movieAdapter = MovieAdapter()
            rcvMovies.adapter = movieAdapter
            rcvMovies.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        movieViewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieAdapter?.submitList(movies)
        }
    }
}