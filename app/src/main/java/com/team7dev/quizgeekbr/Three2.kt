package com.team7dev.quizgeekbr
import android.content.Intent
import android.os.Bundle
import android.view.View

class Three2 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three2)
    }
    @Suppress("UNUSED_PARAMETER")
    fun iniciar(view: View) {
        val intent = Intent(this, Two::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER")//game of thrones
    fun iniciargot(view: View) {
        val intent = Intent(this, Eleven::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER")//stranger things
    fun iniciarst(view: View) {
        val intent = Intent(this, Twelve::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER")//the mandalorian
    fun iniciartm(view: View) {
        val intent = Intent(this, Thirteen::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER")//the witcher
    fun iniciartw(view: View) {
        val intent = Intent(this, Fourteen::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER")//Breaking Bad
    fun iniciarbb(view: View) {
        val intent = Intent(this, Fifteen::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER")//The boys
    fun iniciartb(view: View) {
        val intent = Intent(this, Eighteen::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER")//prision break
    fun iniciarpb(view: View) {
        val intent = Intent(this, Sixteen ::class.java)
        startActivity(intent)

    }
    @Suppress("UNUSED_PARAMETER")//supernatural
    fun iniciarsn(view: View) {
        val intent = Intent(this, Seventeen ::class.java)
        startActivity(intent)

    }
}
