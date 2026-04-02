package com.example.recyclerviewex.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recyclerviewex.R
import com.example.recyclerviewex.data.model.Cast
import com.example.recyclerviewex.data.model.Genre
import com.example.recyclerviewex.databinding.FragmentMovieDetailBinding
import com.example.recyclerviewex.databinding.ItemGenreBinding

/**
 * Task 1:
 * Tạo fragment [MovieDetailFragment]
 * và tạo UI cho màn hình Movie Detail [FragmentMovieDetailBinding]
 */

class MovieDetailFragment : Fragment() {

    var binding: FragmentMovieDetailBinding? = null
    val movieDetailViewModel: MovieDetailViewModel by viewModels()

    var castAdapter: CastAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Task 2.2: Nhận movie id từ màn hình trước
        val movieId = arguments?.getInt("movieId") ?: -1
        Log.d("movieId", "$movieId")

        //Task 3.4 call api
        movieDetailViewModel.getMovieDetail(movieId)

        binding?.imgBack?.setOnClickListener {
            findNavController().popBackStack()
        }

        observeData()
    }

    private fun observeData() {
        /**
         * Task 7: Bind dữ liệu lên UI
         */
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
            updateData(movieDetail)
        }
    }

    private fun updateData(movieDetail: MovieDetailUiModel) = binding?.run {
        Glide.with(this@MovieDetailFragment)
            .load(movieDetail.posterPath)
            .into(imgPoster)
        txtTitle.text = movieDetail.title
        txtOverview.text = movieDetail.overview
        txtRate.text = getString(R.string.rate_format, movieDetail.voteAverage)
        txtTotalVote.text = movieDetail.voteCount?.toString()
        txtCompany.text = movieDetail.productionCompanies

        bindGenres(movieDetail.genres ?: emptyList())
        bindCastList(movieDetail.cast ?: emptyList())
    }

    fun bindGenres(genres: List<Genre>) = binding?.run {
        //cần thêm thư viện flexbox trong file gradle [app]
        genres.forEach { genre ->
            val itemBinding = ItemGenreBinding.inflate(
                layoutInflater,
                flexGenres,
                false
            )
            itemBinding.tvText.text = genre.name
            flexGenres.addView(itemBinding.root)
        }
    }

    fun bindCastList(castList: List<Cast>) = binding?.run {
        /**
         * Task 8.1: Tạo [CastAdapter]
         */
        castAdapter = CastAdapter()
        rcvCast.adapter = castAdapter
        rcvCast.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )


        /**
         * Task 8.5: Hiển thị danh sách Cast
         */
        castAdapter?.submitList(castList)
    }
}