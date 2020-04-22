package com.lpu.movieapp.Pack_data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lpu.movieapp.Pack_data.api.moviedb
import com.lpu.movieapp.Pack_data.vo_pojo.details_of_movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class movie_data_source(private val apiservie:moviedb,private val compositeDisposable: CompositeDisposable) {
    private val _networkState  = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downlaodMovieResponse = MutableLiveData<details_of_movie>()
    val downloadMovieRespone:LiveData<details_of_movie>
        get()=_downlaodMovieResponse
    fun fetchMovieDetails(movieId:Int)
    {
        _networkState.postValue(NetworkState.LOADING)
        try {
           compositeDisposable.add(
               apiservie.getDetailsOfMovie(movieId)
                   .subscribeOn(Schedulers.io())
                   .subscribe(
                       {
                           _downlaodMovieResponse.postValue(it)
                           _networkState.postValue(NetworkState.LOADED)
                       },
                       {
                           _networkState.postValue(NetworkState.ERROR)
                           Log.e("MoviedetailDataSource",it.message)
                       }
                   )
           )
        }
        catch (e:Exception)
        {
            Log.e("MoviedetailDataSource",e.message)
        }
    }
}