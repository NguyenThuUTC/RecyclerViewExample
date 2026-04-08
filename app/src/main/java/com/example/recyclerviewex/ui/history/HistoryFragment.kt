package com.example.recyclerviewex.ui.history

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
import com.example.recyclerviewex.databinding.FragmentViewedHistoryBinding
import com.example.recyclerviewex.ui.common.BaseCreateFactoryViewModel
import com.example.recyclerviewex.ui.detail.MovieDetailViewModel
import com.example.recyclerviewex.ui.movies.MovieAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.getValue

class HistoryFragment: Fragment() {
    var binding: FragmentViewedHistoryBinding? = null

    val db by lazy {
        AppDatabaseProvider.getInstance(requireContext().applicationContext)
    }

    private val repository by lazy {
        MovieRepository(db.movieDao())
    }

    val historyViewModel: HistoryViewModel by viewModels {
        BaseCreateFactoryViewModel {
            HistoryViewModel(repository)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewedHistoryBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        observeData()
    }

    fun observeData() {
        historyViewModel.movies.onEach {
            movieAdapter?.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

//        viewLifecycleOwner.lifecycleScope.launch {
//            historyViewModel.movies.collectLatest {
//
//            }
//        }

    }

    var movieAdapter: MovieAdapter? = null
    private fun initViews() {
        movieAdapter = MovieAdapter(onMovieClick = { movie ->
        })
        binding?.run {
            rcvHistory.adapter = movieAdapter
            rcvHistory.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}