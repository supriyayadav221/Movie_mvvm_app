package com.lpu.movieapp.Pack_data.api

import com.lpu.movieapp.Pack_data.vo_pojo.MovieResponse
import com.lpu.movieapp.Pack_data.vo_pojo.details_of_movie
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface moviedb {
    //https://api.themoviedb.org/3/movie/top_rated?api_key=da21ba730ba3be6bf15cde1b2cac11f1
    //https://api.themoviedb.org/3/movie/640344?api_key=da21ba730ba3be6bf15cde1b2cac11f1
    //https://api.themoviedb.org/3/
    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>
    @GET("movie/{movie_id}")
    fun getDetailsOfMovie(@Path("movie_id") id: Int): Single<details_of_movie>
}