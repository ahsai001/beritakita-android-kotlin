package com.ahsailabs.beritakita_kotlin

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //cara 1 : Handler delay
        /*Handler(Looper.getMainLooper()).postDelayed({
            //TODO : set destination after time is up
            openMainPage()
        }, 3000)*/

        //cara 2 :
        object : CountDownTimer(3000, 1000) {
            override fun onTick(l: Long) {}
            override fun onFinish() {
                //TODO : set destination after time is up
                openMainPage()
            }
        }.start()
    }

    private fun openMainPage() {
        MainActivity.start(this@SplashActivity)
        finish()
    }
}