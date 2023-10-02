package com.example.aplikasigithubuser.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.ui.viewmodel.ThemeViewModel
import com.example.aplikasigithubuser.ui.viewmodel.ViewModelFactory
import com.example.aplikasigithubuser.utils.SettingPreferences
import com.example.aplikasigithubuser.utils.dataStore

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        val pref = SettingPreferences.getInstance(application.dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            ThemeViewModel::class.java
        )
        mainViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        }

        val splashScreenDuration = 1000
        val mainActivityIntent = Intent(this, MainActivity::class.java)

        Thread {
            Thread.sleep(splashScreenDuration.toLong())
            startActivity(mainActivityIntent)
            finish()
        }.start()
    }
}