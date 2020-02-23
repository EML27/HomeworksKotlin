package com.example.homeworkskotlin

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun weatherByName(@Query("q") name: String)
}