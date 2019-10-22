package com.example.homeworkskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_go_main.setOnClickListener{
            intent = Intent(applicationContext, HiddenIntentSender::class.java)
            startActivity(intent)
        }
    }
}
