package com.example.homeworkskotlin.response

import com.google.gson.annotations.SerializedName

data class WeatherList(@SerializedName("list") var list: List<WeatherResponse>)