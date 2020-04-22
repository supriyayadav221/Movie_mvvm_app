package com.lpu.movieapp.ui.top_rated_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lpu.movieapp.Pack_data.api.POST_PER_PAGE
import com.lpu.movieapp.Pack_data.api.moviedb
import com.lpu.movieapp.Pack_data.repository.MovieDataSource
import com.lpu.movieapp.Pack_data.repository.MovieDataSourceFactory
import com.lpu.movieapp.Pack_data.repository.NetworkState
import com.lpu.movieapp.Pack_data.vo_pojo.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePageListRepository (private val apiService : moviedb) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }
}