package com.example.cine.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: MovieService by lazy{
        Retrofit.Builder()
            .baseUrl("https://192.168.0.37:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }
}