package com.example.homeworkskotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MusicPlayerService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    var songPos = 0
    var currentTrack: Track = tracksList.getData()[0]

    var mBinder = MyBinder()
    private val CHANNEL_ID = "27"

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

    private fun setTrack(track: Track) {
        songPos = tracksList.getPosition(track)
        currentTrack = track
        stopMusic()
        mediaPlayer = MediaPlayer.create(this, currentTrack.src)
        playMusic()
        setNotification()
    }

    private fun playMusic() {
        mediaPlayer?.start()
        setNotification()
    }

    private fun pauseMusic() {
        mediaPlayer?.pause()
        setNotification()
    }

    private fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer = null
        setNotification()
    }

    private fun nextTrack() {
        songPos++
        setTrack(tracksList.getTrackByNumber(songPos))
    }

    private fun previousTrack() {
        songPos--
        setTrack(tracksList.getTrackByNumber(songPos))
    }

    private fun musicIsPlaying(): Boolean = mediaPlayer?.isPlaying ?: false

    private fun setNotification() {
        val track = currentTrack
        //Нагло украдено
        val getIntent =
            { action: String -> Intent(this, MusicPlayerService::class.java).setAction(action) }
        val getPendingIntent =
            { action: String -> PendingIntent.getService(this, 0, getIntent(action), 0) }

        val currentStateIcon: Int
        val currentStateCommand: String
        val currentStateTitle: String
        if (musicIsPlaying()) {
            currentStateIcon = R.drawable.ic_pause
            currentStateCommand = Commands.STOP
            currentStateTitle = "Pause"
        } else {
            currentStateIcon = R.drawable.ic_play
            currentStateCommand = Commands.START
            currentStateTitle = "Play"
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification_channel_name)
            val descriptionText = getString(R.string.notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                notificationManager.getNotificationChannel(CHANNEL_ID) ?: NotificationChannel(
                    CHANNEL_ID,
                    name,
                    importance
                ).apply {
                    description = descriptionText
                }
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_play)
            .setContentTitle(track.name)
            .setContentText(track.author)
            .setLargeIcon(BitmapFactory.decodeResource(resources, track.coverSrc))
            .addAction(R.drawable.ic_prev, "Previous", getPendingIntent(Commands.PREV))
            .addAction(currentStateIcon, currentStateTitle, getPendingIntent(currentStateCommand))
            .addAction(R.drawable.ic_next, "Next", getPendingIntent(Commands.NEXT))
            .build()

        notificationManager.notify(1, notification)
    }
}
