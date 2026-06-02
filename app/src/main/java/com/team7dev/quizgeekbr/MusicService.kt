package com.team7dev.quizgeekbr

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.IBinder

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var musicControlReceiver: BroadcastReceiver

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.sound_background)
        mediaPlayer.isLooping = true // Repetir música continuamente
        mediaPlayer.setVolume(0.10f, 0.10f) // Configura o volume para 10%
        mediaPlayer.start() // Inicia a música

        // Inicializa o BroadcastReceiver para controlar a música
        musicControlReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent?.getStringExtra("action")
                if (action != null) {
                    handleAction(action)
                }
            }
        }

        // Registra o BroadcastReceiver para ouvir comandos de controle de música
        val filter = IntentFilter("com.team7dev.quizgeekbr.MUSIC_CONTROL")
        registerReceiver(musicControlReceiver, filter)
    }

    private fun handleAction(action: String) {
        when (action) {
            "play" -> {
                if (!mediaPlayer.isPlaying) {
                    mediaPlayer.start()
                }
            }
            "pause" -> {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(musicControlReceiver) // Desregistra o BroadcastReceiver
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
