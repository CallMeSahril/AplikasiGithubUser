package com.example.aplikasigithubuser.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.local.database.GithubUser
import com.example.aplikasigithubuser.databinding.ActivityFavoriteBinding
import com.example.aplikasigithubuser.ui.adapter.GithubUserAdapter
import com.example.aplikasigithubuser.ui.viewmodel.FavoriteModelFactory
import com.example.aplikasigithubuser.ui.viewmodel.MainViewModel


class FavoriteActivity : AppCompatActivity(), GithubUserAdapter.OnItemClickListener {
    private var _activityMainBinding: ActivityFavoriteBinding? = null
    private val binding get() = _activityMainBinding
    private lateinit var adapter: GithubUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favorite)


        _activityMainBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val mainViewModel = obtainViewModel(this@FavoriteActivity)
        mainViewModel.getAllNotes().observe(this) { noteList ->
            if (noteList != null) {
                adapter.setListNotes(noteList)
            }
        }
        adapter = GithubUserAdapter(this)
        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)
        binding?.rvNotes?.setHasFixedSize(true)
        binding?.rvNotes?.adapter = adapter

    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = FavoriteModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

    override fun onItemClick(item: GithubUser) {
        val username = item.nameGithubUser
        val intent = Intent(this@FavoriteActivity, MainActivity::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
    }
}