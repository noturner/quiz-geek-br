package com.team7dev.quizgeekbr
import android.content.Intent
import android.os.Bundle
import android.view.View

class Two : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
    }

    @Suppress("UNUSED_PARAMETER")
    fun iniciar (view: View) {
        val intent = Intent(this, Three2::class.java)
        startActivity(intent)
    }
    @Suppress("UNUSED_PARAMETER")
    fun iniciar2 (view: View) {
        val intent = Intent(this, Three::class.java)
        startActivity(intent)
    }

}

