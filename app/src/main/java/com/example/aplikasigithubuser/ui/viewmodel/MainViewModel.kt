package com.example.aplikasigithubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.example.aplikasigithubuser.data.local.database.GithubUser
import com.example.aplikasigithubuser.repository.GithubUserRepository

class MainViewModel(application: Application) : ViewModel() {
    private val mGithubUserRepository: GithubUserRepository = GithubUserRepository(application)

    fun getAllNotes(): LiveData<List<GithubUser>> = mGithubUserRepository.getAllNotes()
}