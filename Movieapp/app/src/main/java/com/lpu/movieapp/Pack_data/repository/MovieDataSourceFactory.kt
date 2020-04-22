package com.lpu.movieapp.Pack_data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.lpu.movieapp.Pack_data.api.moviedb
import com.lpu.movieapp.Pack_data.vo_pojo.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory (private val apiService : moviedb, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(apiService,compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}