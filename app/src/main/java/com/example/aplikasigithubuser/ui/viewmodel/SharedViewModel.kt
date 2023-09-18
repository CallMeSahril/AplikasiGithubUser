package com.example.aplikasigithubuser.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//class com.example.aplikasigithubuser.ui.viewmodel.SharedViewModel : ViewModel() {
//    private val _selectedUsername = MutableLiveData<String>()
//    val selectedUsername: LiveData<String> = _selectedUsername
//
//    fun setSelectedUsername(username: String) {
//        _selectedUsername.value = username
//    }
//}


class SharedViewModel : ViewModel() {
    // Buat LiveData untuk menyimpan data yang akan dibagikan
    val sharedData: MutableLiveData<String> = MutableLiveData()
}
