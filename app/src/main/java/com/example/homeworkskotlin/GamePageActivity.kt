package com.example.homeworkskotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game_page.*

class GamePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)
        val gameName = intent?.extras?.getString(GAME_NAME) ?: "DEFAULT NAME"
        val gameType = intent?.extras?.getString(GAME_TYPE) ?: "DEFAULT TYPE"
        val imgSrc = intent?.extras?.getInt(GAME_PIC_SRC) ?: 0
        img_main.setImageResource(imgSrc)
        tv_main_name.text = gameName
        tv_main_type.text = gameType
    }

    companion object {
        private const val GAME_NAME = "nameg"
        private const val GAME_TYPE = "typeg"
        private const val GAME_PIC_SRC = "picg"

        fun createIntent(activity: Activity, name: String, type: String, src: Int) =
            Intent(activity, GamePageActivity::class.java).apply { putExtra(GAME_NAME, name)
            putExtra(GAME_TYPE,type)
            putExtra(GAME_PIC_SRC, src)}

    }
}
