package com.example.homeworkskotlin

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class MusicPlayerService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    var songPos = 0
    var currentTrack: Track = tracksList.getData()[0]

    var mBinder = MyBinder()

    override fun onBind(intent: Intent?): IBinder? = mBinder

    inner class MyBinder : Binder() {
        fun getService() = this@MusicPlayerService
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        currentTrack =
            tracksList.getTrackByNumber(intent.extras?.getInt("track position") ?: songPos)
        setTrack(currentTrack)
        when (intent.action) {
            Commands.NEXT -> nextTrack()
            Commands.PREV -> previousTrack()
            Commands.PAUSE -> pauseMusic()
            Commands.STOP -> stopMusic()
            Commands.START -> playMusic()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    fun setTrack(track: Track) {
        songPos = tracksList.getPosition(track)
        currentTrack = track
        stopMusic()
        mediaPlayer = MediaPlayer.create(this, currentTrack.src)
        playMusic()
    }

    fun playMusic() {
        mediaPlayer?.start()

    }

    fun pauseMusic() {
        mediaPlayer?.pause()

    }

    fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer = null

    }

    fun nextTrack() {
        songPos++
        if (songPos >= tracksList.getData().size) {
            songPos = 0
        }
        setTrack(tracksList.getTrackByNumber(songPos))
    }

    fun previousTrack() {
        songPos--
        if (songPos < 0) {
            songPos = tracksList.getData().size - 1
        }
        setTrack(tracksList.getTrackByNumber(songPos))
    }

    fun musicIsPlaying(): Boolean? = mediaPlayer?.isPlaying

    /*companion object {
        lateinit var res: MusicPlayerService
        fun create(activity: Activity): MusicPlayerService {
            activity.bindService(
                Intent(activity, MusicPlayerService::class.java),
                mConnection,
                Context.BIND_AUTO_CREATE
            )
            return res
        }

        var mConnection = object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                var bind = service as MyBinder
                res = bind.getService()
            }
        }
    }
*/

}
