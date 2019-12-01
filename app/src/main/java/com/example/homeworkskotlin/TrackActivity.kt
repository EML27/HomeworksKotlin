package com.example.homeworkskotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_track.*

class TrackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)
        var track = tracksList.getData()[intent?.extras?.getInt(TRACK_POSITION) ?: 0]
        tv_song_name_activity.text =
            track.name
        tv_song_author_activity.text =
            track.author
        cover_activity_img.setImageResource(
            track.coverSrc
        )
    }

    companion object {
        private const val TRACK_POSITION = "track position"

        fun createIntent(activity: Activity, trackPos: Int) =
            Intent(activity, TrackActivity::class.java).apply {
                putExtra(
                    TRACK_POSITION, trackPos
                )
            }
    }
}
