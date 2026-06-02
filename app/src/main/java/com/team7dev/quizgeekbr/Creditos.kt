package com.team7dev.quizgeekbr

import android.content.Intent
import android.os.Bundle
import android.view.View

class Creditos : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creditos)
    }
    @Suppress("UNUSED_PARAMETER")
    fun homeinicio(view: View) {
        val intent = Intent(this, Inicial::class.java)
        startActivity(intent)
    }
}