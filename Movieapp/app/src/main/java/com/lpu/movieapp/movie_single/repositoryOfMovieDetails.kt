package com.lpu.movieapp.movie_single

import androidx.lifecycle.LiveData
import com.lpu.movieapp.Pack_data.api.moviedb
import com.lpu.movieapp.Pack_data.repository.NetworkState
import com.lpu.movieapp.Pack_data.repository.movie_data_source
import com.lpu.movieapp.Pack_data.vo_pojo.details_of_movie
import io.reactivex.disposables.CompositeDisposable

class repositoryOfMovieDetails(private  val apiService: moviedb) {
    lateinit var moviedetailsdatasource: movie_data_source
    fun fetchsinglemoviedetails(compositeDisposable: CompositeDisposable,movieId:Int):LiveData<details_of_movie>
    {
        moviedetailsdatasource= movie_data_source(apiService,compositeDisposable)
        moviedetailsdatasource.fetchMovieDetails(movieId)
        return moviedetailsdatasource.downloadMovieRespone

    }
    fun getMovieDetailsNetworkState():LiveData<NetworkState>
    {
        return moviedetailsdatasource.networkState
    }
}