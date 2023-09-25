package com.example.aplikasigithubuser.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasigithubuser.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splashScreenDuration = 1000
        val mainActivityIntent = Intent(this, MainActivity::class.java)

        Thread {
            Thread.sleep(splashScreenDuration.toLong())
            startActivity(mainActivityIntent)
            finish()
        }.start()
    }
}