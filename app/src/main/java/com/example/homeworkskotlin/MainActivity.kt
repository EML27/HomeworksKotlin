package com.example.homeworkskotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var service: WeatherService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        go_button.setOnClickListener{
//            intent = Intent(applicationContext, MainActivity::class.java)
//            startActivity(intent)
//        }
        service = ApiFactory.weatherService
        service.weatherByName("Kazan")
    }
}
