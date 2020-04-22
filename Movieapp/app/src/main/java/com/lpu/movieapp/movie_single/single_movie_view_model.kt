package com.lpu.movieapp.movie_single

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lpu.movieapp.Pack_data.repository.NetworkState
import com.lpu.movieapp.Pack_data.vo_pojo.details_of_movie
import io.reactivex.disposables.CompositeDisposable

class single_movie_view_model(private  val movierepository:repositoryOfMovieDetails,movieId:Int) : ViewModel() {
    private  val compositeDisposable=CompositeDisposable()
    val movieDetails:LiveData<details_of_movie>by lazy {
        movierepository.fetchsinglemoviedetails(compositeDisposable, movieId)
    }
        val networkState:LiveData<NetworkState>by lazy {
            movierepository.getMovieDetailsNetworkState()
        }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}