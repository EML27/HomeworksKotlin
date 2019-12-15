package com.example.homeworkskotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_track.*

class TrackActivity : AppCompatActivity() {
    //    var bound: Boolean = false
    // var playerService: MusicPlayerService? = null
    private var musicIsPlaying = true
    private lateinit var currentTrack: Track

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)
        var track = tracksList.getData()[intent?.extras?.getInt(TRACK_POSITION) ?: 0]
        currentTrack = track
        setTrackViews(currentTrack)


        // playerService?.setTrack(track)

        startTrack(track)
//        playerService = MusicPlayerService.create(this)
//        bound=true


        pause_btn.setOnClickListener {
            if (!musicIsPlaying) {
                startServiceWithCommand(Commands.START)
                pause_btn.setImageResource(R.drawable.ic_pause)

                musicIsPlaying = true
            } else {
                startServiceWithCommand(Commands.PAUSE)
                pause_btn.setImageResource(R.drawable.ic_play)
                musicIsPlaying = false
            }

        }

        next_btn.setOnClickListener {
            currentTrack = tracksList.getTrackByNumber(tracksList.getPosition(currentTrack) + 1)
            startTrack(currentTrack)
            setTrackViews(currentTrack)
        }

        previous_btn.setOnClickListener {
            currentTrack = tracksList.getTrackByNumber(tracksList.getPosition(currentTrack) - 1)
            startTrack(currentTrack)
            setTrackViews(currentTrack)
        }
    }

    companion object {
        private const val TRACK_POSITION = "track position"

        fun createIntent(activity: Activity, trackPos: Int) =
            Intent(activity, TrackActivity::class.java).apply {
                putExtra(
                    TRACK_POSITION, trackPos
                )
            }
        /*
        fun createIntent(activity: Activity, trackPos: Int,mp: MusicPlayerService?) =
            Intent(activity, TrackActivity::class.java).apply {
                putExtra(TRACK_POSITION, trackPos)


            }*/
        //Да как передать ему контроль над сервисом


    }

//    private fun checkIfPlayerIsNull() {
//        if (playerService == null) {
//            Toast.makeText(this, "player is null", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "player is not null", Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun startServiceWithCommand(command: String) {
        startService(Intent(this, MusicPlayerService::class.java).apply { action = command })
    }

    private fun startTrack(track: Track) {
        startService(Intent(this, MusicPlayerService::class.java).apply {
            putExtra("track pos", tracksList.getPosition(track))
            action = Commands.START
            musicIsPlaying = true
        })
    }

    private fun setTrackViews(track: Track) {
        tv_song_name_activity.text =
            track.name
        tv_song_author_activity.text =
            track.author
        cover_activity_img.setImageResource(
            track.coverSrc
        )
    }
}
