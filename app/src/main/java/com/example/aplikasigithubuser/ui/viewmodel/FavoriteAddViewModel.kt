package com.example.aplikasigithubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.aplikasigithubuser.data.local.entity.Favorite
import com.example.aplikasigithubuser.data.repository.FavoriteRepository

class FavoriteAddViewModel(application: Application):ViewModel() {
    private  val mFavoriteRepository : FavoriteRepository = FavoriteRepository(application)

    fun insert(favorite: Favorite) {
        mFavoriteRepository.insert(favorite)
    }

    fun delete(favorite: Favorite) {
        mFavoriteRepository.delete(favorite)
    }
}