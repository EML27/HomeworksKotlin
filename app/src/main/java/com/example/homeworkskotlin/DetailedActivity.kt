package com.example.homeworkskotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.homeworkskotlin.detrecycler.DataPair
import com.example.homeworkskotlin.detrecycler.DetWeatherAdapter
import com.example.homeworkskotlin.response.WeatherResponse
import kotlinx.android.synthetic.main.activity_detailed.*
import kotlinx.coroutines.*
import java.util.*

class DetailedActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var service: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        service = ApiFactory.weatherService


        launch {
            val response = withContext(Dispatchers.IO) {
                service.weatherById(intent.extras.getInt(CITY_ID), "metric")
            }

            city_detailed_name_tv.text = response.name
            status_detailed_tv.text = response.weather[0].main
            temp_detailed_tv.text = response.main.temp.toString() + " ℃"
            feels_like_detailed_tv.text = response.main.feelsLike.toString() + " ℃"

            var picUrl = "https://openweathermap.org/img/wn/${response.weather[0].icon}@2x.png"
            Glide
                .with(this@DetailedActivity)
                .load(picUrl)
                .into(weather_pic)
            val x = response.main.temp.toInt()
            var chosenColor = R.color.colorSecondaryText
            when {
                x < -40 -> chosenColor = R.color.tempColdAsFuk
                x < -30 && x >= -40 -> chosenColor = R.color.tempVeryCold
                x < -10 && x >= -30 -> chosenColor = R.color.tempCold
                x < 5 && x >= -10 -> chosenColor = R.color.tempCool
                x in 5..14 -> chosenColor = R.color.tempWarm
                x in 15..24 -> chosenColor = R.color.tempHot
                x in 25..34 -> chosenColor = R.color.tempVeryHot
                x >= 35 -> chosenColor = R.color.tempHotDog
            }

            temp_detailed_tv.setTextColor(getColor(chosenColor))
            setRecInfo(response)
        }
    }

    private fun setRecInfo(response: WeatherResponse) {
        var list: LinkedList<DataPair> = LinkedList()
        list.add(DataPair("Pressure", response.main.pressure.toString() + " hpa"))
        list.add(DataPair("Wind speed", response.wind.speed.toString() + " m/s"))
        var windOrientation = "gay"
        var x = response.wind.deg
        when {
            x < 30 || x >= 330 -> windOrientation = "East"
            x < 60 -> windOrientation = "Northeast"
            x < 120 -> windOrientation = "North"
            x < 150 -> windOrientation = "Northwest"
            x < 210 -> windOrientation = "West"
            x < 240 -> windOrientation = "Southwest"
            x < 300 -> windOrientation = "South"
            x < 330 -> windOrientation = "Southeast"
        }
        list.add(DataPair("Wind orientation", windOrientation))
        list.add(DataPair("Humidity", response.main.humidity.toString() + "%"))
        list.add(DataPair("Clouds", response.weather[0].description.capitalize()))
        list.add(DataPair("Max temperature", response.main.tempMax.toString() + " ℃"))
        list.add(DataPair("Min temperature", response.main.tempMin.toString() + " ℃"))

        detailed_rec.adapter = DetWeatherAdapter(list)
    }

    companion object {

        private const val CITY_ID = "cityId"

        fun createIntent(activity: Activity, cityId: Int) =
            Intent(activity, DetailedActivity::class.java).apply {
                putExtra(
                    CITY_ID, cityId
                )
            }
    }
}
