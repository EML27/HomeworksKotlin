package com.example.homeworkskotlin

import android.app.ActivityOptions
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworkskotlin.rv_classes.RVAdapter
import kotlinx.android.synthetic.main.activity_music_list.*

class MusicListActivity : AppCompatActivity() {

//    var musicPlayer: MusicPlayerService? = null

    var playerService: MusicPlayerService? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_list)

        bindService(
            Intent(this, MusicPlayerService::class.java),
            mConnection,
            Context.BIND_AUTO_CREATE
        )

        rv.adapter = RVAdapter(tracksList.getData()) { track, view ->
            startActivity(
                TrackActivity.createIntent(this, tracksList.getPosition(track)),
                ActivityOptions.makeSceneTransitionAnimation(this, view, "cover").toBundle()
                //нашел в интернете гайд на shared element transition и не удержался чтобы заюзать
            )
        }
    }


    override fun onStop() {
        super.onStop()
        playerService?.let {
            unbindService(mConnection)
            playerService = null
        }
    }

    private var mConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            playerService = null
            //Toast.makeText(this@TrackActivity,"Entered onServiceDisconnected block", Toast.LENGTH_SHORT).show()
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicPlayerService.MyBinder
            playerService = binder.getService()
            //Toast.makeText(this@TrackActivity,"Entered onServiceConnected block", Toast.LENGTH_SHORT).show()
        }
    }
}
