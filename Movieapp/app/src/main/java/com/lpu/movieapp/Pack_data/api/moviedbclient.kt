package com.lpu.movieapp.Pack_data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient;
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val API_KEY="da21ba730ba3be6bf15cde1b2cac11f1"
const val BASE_URL="https://api.themoviedb.org/3/"
const val POSTER_BASE_URL="http://image.tmdb.org/t/p/w185"
const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20
//4XzbcAKdX4n3aWfzBjjeAlm68S3.jpg
object moviedbclient {
    fun getClient():moviedb{
        val requestInterceptor = Interceptor{chain->
            val url=chain.request().url().newBuilder().addQueryParameter("api_key", API_KEY).build()
            val request=chain.request().newBuilder().url(url).build()
            return@Interceptor chain.proceed(request)
        }

        val okHttpClient=OkHttpClient.Builder().addInterceptor(requestInterceptor).connectTimeout(60,TimeUnit.SECONDS).build()

        return Retrofit.Builder()
            .client(okHttpClient).baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build().create(moviedb::class.java)

    }
}