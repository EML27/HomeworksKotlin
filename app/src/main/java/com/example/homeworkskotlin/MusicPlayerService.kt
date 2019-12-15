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
            tracksList.getTrackByNumber(intent.extras?.getInt("track pos") ?: songPos)
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
        setTrack(tracksList.getTrackByNumber(songPos))
    }

    fun previousTrack() {
        songPos--
        setTrack(tracksList.getTrackByNumber(songPos))
    }

    fun musicIsPlaying(): Boolean? = mediaPlayer?.isPlaying


}
