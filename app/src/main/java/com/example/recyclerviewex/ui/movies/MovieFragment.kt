package com.example.recyclerviewex.ui.movies

import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    var layoutManager: RecyclerView.LayoutManager? = null

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
        initViews()
        initActions()
        observeData()
    }

    private fun initActions() = binding?.run {
        rcvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0) return
                val totalItemCount = layoutManager?.itemCount ?: 0
                val lastVisibleItem = (layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition() ?: 0
                val firstVisibleItem = (layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition()

                Log.d("addOnScrollListener", "firstVisibleItem ${firstVisibleItem}  lastVisibleItem $lastVisibleItem")
                if (lastVisibleItem >= totalItemCount - 5) {
                    movieViewModel.loadMovies()
                }
            }
        })
    }

    private fun observeData() {
        movieViewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieAdapter?.submitList(movies ?: listOf())
        }
    }

    private fun initViews() {
        movieAdapter = MovieAdapter(onMovieClick = { movie ->
            Toast.makeText(this.requireContext(), "${movie.title}", Toast.LENGTH_LONG).show()
        })
        binding?.run {
            rcvMovies.adapter = movieAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            rcvMovies.layoutManager = layoutManager
        }
    }
}
