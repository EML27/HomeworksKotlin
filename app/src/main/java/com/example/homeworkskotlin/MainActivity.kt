package com.example.homeworkskotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        go_button.setOnClickListener{
            intent = Intent(applicationContext, MusicListActivity::class.java)
            startActivity(intent)
        }
    }
}
