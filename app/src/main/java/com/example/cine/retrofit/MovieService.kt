package com.example.cine.retrofit

import com.example.cine.models.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("/api/movies")
    suspend fun getMovies(): Response<List<Movie>>
}