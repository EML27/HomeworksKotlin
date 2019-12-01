package com.example.homeworkskotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworkskotlin.rv_classes.RVAdapter
import kotlinx.android.synthetic.main.activity_music_list.*

class MusicListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_list)

        rv.adapter = RVAdapter(tracksList.getData()) { track ->
            startActivity(TrackActivity.createIntent(this, tracksList.getPosition(track)))
        }
    }
}
