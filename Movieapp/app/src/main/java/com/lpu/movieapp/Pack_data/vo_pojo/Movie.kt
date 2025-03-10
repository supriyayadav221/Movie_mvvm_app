package com.lpu.movieapp.Pack_data.vo_pojo


import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("id")
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String

)