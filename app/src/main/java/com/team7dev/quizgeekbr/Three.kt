package com.team7dev.quizgeekbr
import android.content.Intent
import android.os.Bundle
import android.view.View

class Three : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
    }
    @Suppress("UNUSED_PARAMETER")
    fun iniciar(view: View) {
        val intent = Intent(this, Two::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER") //dragon ball
    fun iniciardb(view: View) {
        val intent = Intent(this, Four::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER") //naruto
    fun iniciarnt (view: View) {
        val intent = Intent(this, Five::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER") //one piece
    fun iniciarop (view: View) {
        val intent = Intent(this, Six::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER") //jujutsu kaisen
    fun iniciarjk (view: View) {
        val intent = Intent(this, Seven::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER") //attack in titans
    fun iniciarat (view: View) {
        val intent = Intent(this, Eight::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER") //demon slayer
    fun iniciards (view: View) {
        val intent = Intent(this, Nine::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER") //fullmetal alchemist brotherhood
    fun iniciarfab (view: View) {
        val intent = Intent(this, Ten::class.java)
        startActivity(intent)

    }
}