package com.example.aplikasigithubuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasigithubuser.data.remote.response.DetailResponse
import com.example.aplikasigithubuser.data.remote.response.GithubResponse
import com.example.aplikasigithubuser.data.remote.response.Itemsitem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _getUser = MutableLiveData<DetailResponse>()
    val GetUser: LiveData<DetailResponse> = _getUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "DetailViewModel"
    }

    fun fetchGetUser(username: String? = null) {
        _isLoading.postValue(true)

        val service = ApiConfig.getApiService()
        val call = service.getUser(username!!)

        call.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(

                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful) {
                    val githubResponse = response.body()
                    val itemsList = githubResponse
                    _getUser.postValue(itemsList!!)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }

                _isLoading.postValue(false) // Set isLoading to false after fetching data
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e(TAG, "Fetch followers failed. Error: ${t.message}")
                // Handle network error here
                _isLoading.postValue(false) // Set isLoading to false if there's an error
            }


        })
    }
}