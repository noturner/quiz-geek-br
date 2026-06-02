package com.team7dev.quizgeekbr

import android.content.Intent
import android.os.Bundle
import android.view.View

class Inicial : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)
    }

    @Suppress("UNUSED_PARAMETER")
    fun iniciar(view: View) {
        val intent = Intent(this, Two::class.java)
        startActivity(intent)
    }
    @Suppress("UNUSED_PARAMETER")
    fun iniciarcreditos(view: View) {
        val intent = Intent(this, Creditos::class.java)
        startActivity(intent)
    }
}
