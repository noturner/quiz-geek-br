package com.team7dev.quizgeekbr

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView


class SplashActivity : BaseActivity() {

    private lateinit var videoView: VideoView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        videoView = findViewById(R.id.splashVideo)

        val videoPath = "android.resource://" + packageName + "/" + R.raw.intro
        val videoUri = Uri.parse(videoPath)

        videoView.setVideoURI(videoUri)
        videoView.setOnCompletionListener {
            startActivity(Intent(this@SplashActivity, Inicial::class.java))
            finish()
        }

        videoView.start()
    }
}
