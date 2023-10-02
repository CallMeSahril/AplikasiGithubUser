package com.example.aplikasigithubuser.ui.activity

import com.example.aplikasigithubuser.ui.fragment.HomeFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.ui.fragment.DetailFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val username = intent.getStringExtra("username")

        // Gunakan data username sesuai kebutuhan Anda
        if (username != null) {
            val fragmentManager = supportFragmentManager
            val detailFragment = DetailFragment()

            // Buat bundle untuk mengirim data username ke DetailFragment
            val bundle = Bundle()
            bundle.putString("username", username)
            detailFragment.arguments = bundle

            val fragment = fragmentManager.findFragmentByTag(DetailFragment::class.java.simpleName)
            if (fragment !is DetailFragment) {
                Log.d("MyFlexibleFragment", "Fragment Name :" + DetailFragment::class.java.simpleName)
                fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, detailFragment, DetailFragment::class.java.simpleName)
                    .commit()
            }
        } else{
            val fragmentManager = supportFragmentManager
            val homeFragment = HomeFragment()
            val fragment = fragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)
            if (fragment !is HomeFragment) {
                Log.d("MyFlexibleFragment", "Fragment Name :" + HomeFragment::class.java.simpleName)
                fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, homeFragment, HomeFragment::class.java.simpleName)
                    .commit()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Buat menu Anda di sini
        return super.onCreateOptionsMenu(menu)
    }
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val fragment = supportFragmentManager.findFragmentById(R.id.frame_container)

        // Jika fragment yang aktif adalah fragment detail, sembunyikan menu
        if (fragment is DetailFragment) {
            supportActionBar?.hide()
        }

        return super.onPrepareOptionsMenu(menu)
    }
}
