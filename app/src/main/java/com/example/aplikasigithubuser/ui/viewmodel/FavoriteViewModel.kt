package com.example.aplikasigithubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasigithubuser.data.local.entity.Favorite
import com.example.aplikasigithubuser.data.repository.FavoriteRepository

class FavoriteViewModel(application: Application):ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)
    fun getAllNotes(): LiveData<List<Favorite>> = mFavoriteRepository.getAllNotes()
}