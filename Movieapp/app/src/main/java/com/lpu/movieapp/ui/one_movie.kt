package com.lpu.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.lpu.movieapp.Pack_data.api.POSTER_BASE_URL
import com.lpu.movieapp.Pack_data.api.moviedb
import com.lpu.movieapp.Pack_data.api.moviedbclient
import com.lpu.movieapp.Pack_data.repository.NetworkState
import com.lpu.movieapp.Pack_data.vo_pojo.details_of_movie
import com.lpu.movieapp.R
import com.lpu.movieapp.movie_single.repositoryOfMovieDetails
import com.lpu.movieapp.movie_single.single_movie_view_model
import kotlinx.android.synthetic.main.activity_one_movie.*
import java.text.NumberFormat
import java.util.*

class one_movie : AppCompatActivity(), LifecycleOwner {

    private lateinit var viewModel: single_movie_view_model
    private lateinit var movieRepository: repositoryOfMovieDetails
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_movie)


        val movieId: Int = intent.getIntExtra("id", 1)

        val apiService: moviedb = moviedbclient.getClient()
        movieRepository =
            repositoryOfMovieDetails(apiService)

        viewModel = getViewModel(movieId)

        viewModel.movieDetails.observe(this, androidx.lifecycle.Observer { bindUI(it) })

        viewModel.networkState.observe(this, androidx.lifecycle.Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt1.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE

        })
    }

    fun bindUI( it: details_of_movie){
        movie_title.text = it.title
        movie_subtitle.text = it.tagline
        release.text = it.releaseDate
        rating.text = it.rating.toString()
        duration.text = it.runtime.toString() + " minutes"
        summary.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        budget.text = formatCurrency.format(it.budget)
        revenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(movie_pic);


    }


    fun getViewModel(movieId:Int): single_movie_view_model {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return single_movie_view_model(
                    movieRepository,
                    movieId
                ) as T
            }
        })[single_movie_view_model::class.java]
    }
}

