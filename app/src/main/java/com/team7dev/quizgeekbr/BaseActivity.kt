package com.team7dev.quizgeekbr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)

        // Inicia o serviço de música apenas se esta for a primeira atividade criada
        if (!isTaskRoot) {
            return
        }

        val musicServiceIntent = Intent(this, MusicService::class.java)
        startService(musicServiceIntent)
    }
}
