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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val service = ApiFactory.weatherService


        launch {
            val response = withContext(Dispatchers.IO) {
                service.weatherById(intent?.extras?.getInt(CITY_ID) ?: 0)
            }

            city_detailed_name_tv.text = response.name
            status_detailed_tv.text = response.weather[0].main
            temp_detailed_tv.text = response.main.temp.toString() + " ℃"
            feels_like_detailed_tv.text = response.main.feelsLike.toString() + " ℃"

            val picUrl = "https://openweathermap.org/img/wn/${response.weather[0].icon}@2x.png"
            Glide
                .with(this@DetailedActivity)
                .load(picUrl)
                .into(weather_pic)
            val currentTemperature = response.main.temp.toInt()
            var chosenColor = R.color.colorSecondaryText
            when {
                currentTemperature < TempHelper.CAF.maxTemp -> chosenColor = R.color.tempColdAsFuk
                currentTemperature < TempHelper.VC.maxTemp -> chosenColor = R.color.tempVeryCold
                currentTemperature < TempHelper.COLD.maxTemp -> chosenColor = R.color.tempCold
                currentTemperature < TempHelper.COOL.maxTemp -> chosenColor = R.color.tempCool
                currentTemperature < TempHelper.WARM.maxTemp -> chosenColor = R.color.tempWarm
                currentTemperature < TempHelper.HOT.maxTemp -> chosenColor = R.color.tempHot
                currentTemperature < TempHelper.VH.maxTemp -> chosenColor = R.color.tempVeryHot
                currentTemperature >= TempHelper.VH.maxTemp -> chosenColor = R.color.tempHotDog
            }

            temp_detailed_tv.setTextColor(getColor(chosenColor))
            setRecInfo(response)
        }
    }

    private fun setRecInfo(response: WeatherResponse) {
        val list: LinkedList<DataPair> = LinkedList()
        list.add(DataPair("Pressure", response.main.pressure.toString() + " hpa"))
        list.add(DataPair("Wind speed", response.wind.speed.toString() + " m/s"))
        var windOrientation = "gay"
        val windOrientationDegree = response.wind.deg
        when {
            windOrientationDegree < WindHelper.NORTH.maxDeg ||
                    windOrientationDegree >= WindHelper.NORTHWEST.maxDeg -> windOrientation =
                "North"
            windOrientationDegree < WindHelper.NORTHEAST.maxDeg -> windOrientation = "Northeast"
            windOrientationDegree < WindHelper.EAST.maxDeg -> windOrientation = "East"
            windOrientationDegree < WindHelper.SOUTHEAST.maxDeg -> windOrientation = "Southeast"
            windOrientationDegree < WindHelper.SOUTH.maxDeg -> windOrientation = "South"
            windOrientationDegree < WindHelper.SOUTHWEST.maxDeg -> windOrientation = "Southwest"
            windOrientationDegree < WindHelper.WEST.maxDeg -> windOrientation = "West"
            windOrientationDegree < WindHelper.NORTHWEST.maxDeg -> windOrientation = "Northwest"
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
