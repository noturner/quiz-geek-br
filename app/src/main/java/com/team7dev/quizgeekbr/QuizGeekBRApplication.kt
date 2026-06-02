package com.team7dev.quizgeekbr

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle

class QuizGeekBRApplication : Application() {

    private var activityReferences = 0
    private var isActivityChangingConfigurations = false

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

            override fun onActivityStarted(activity: Activity) {
                if (++activityReferences == 1 && !isActivityChangingConfigurations) {
                    // App entrou no foreground
                    sendMusicControlIntent("play")
                }
            }

            override fun onActivityResumed(activity: Activity) {}

            override fun onActivityPaused(activity: Activity) {}

            override fun onActivityStopped(activity: Activity) {
                isActivityChangingConfigurations = activity.isChangingConfigurations
                if (--activityReferences == 0 && !isActivityChangingConfigurations) {
                    // App foi para o background
                    sendMusicControlIntent("pause")
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {}

            private fun sendMusicControlIntent(action: String) {
                val musicIntent = Intent("com.team7dev.quizgeekbr.MUSIC_CONTROL")
                musicIntent.putExtra("action", action)
                sendBroadcast(musicIntent)
            }
        })
    }
}
