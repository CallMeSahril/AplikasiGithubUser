package com.example.aplikasigithubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.example.aplikasigithubuser.data.local.database.GithubUser
import com.example.aplikasigithubuser.repository.GithubUserRepository

class GithubUserAddDeleteViewModel(application: Application) : ViewModel() {

     var mGithubUserRepository: GithubUserRepository = GithubUserRepository(application)
    fun insert(githubUser: GithubUser) {
        mGithubUserRepository.insert(githubUser)
    }

    fun delete(githubUser: GithubUser) {
        mGithubUserRepository.delete(githubUser)
    }

    fun checkUser(username: String): LiveData<GithubUser> {
        return mGithubUserRepository.checkUser(username)
    }



}
