package com.example.homeworkskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_catcher.*
import kotlinx.android.synthetic.main.activity_hidden_intent_sender.*

class CatcherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catcher)
val intent: Intent = intent
        val text=intent.extras?.get(Intent.EXTRA_TEXT).toString()
        tv_catcher.text=text
    }
}
