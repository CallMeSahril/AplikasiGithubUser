package com.example.aplikasigithubuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasigithubuser.data.remote.response.FollowingResponseItem
import com.example.aplikasigithubuser.data.remote.response.GithubResponse
import com.example.aplikasigithubuser.data.remote.response.ItemFollowers
import com.example.aplikasigithubuser.data.remote.response.Itemsitem

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiViewModel : ViewModel() {

    private val _listUser = MutableLiveData<List<Itemsitem>>()
    val listReview: LiveData<List<Itemsitem>> = _listUser

    private val _listFolower = MutableLiveData<List<ItemFollowers>>()
    val listReviewFolower: LiveData<List<ItemFollowers>> = _listFolower

    private val _listFollowing = MutableLiveData<List<FollowingResponseItem>>()
    val listReviewFollowing: LiveData<List<FollowingResponseItem>> = _listFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        fetchDataFromApi()

    }
    fun fetchDataFromApi(username: String = "sahril") {
        _isLoading.postValue(true) // Set isLoading to true while fetching data

        val apiService = ApiConfig.getApiService()
        val call = apiService.searchUsers(username) // Menggunakan parameter username yang diterima

        call.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                if (response.isSuccessful) {
                    val githubResponse = response.body()
                    val itemsList = githubResponse?.items ?: emptyList()
                    _listUser.postValue(itemsList as List<Itemsitem>?)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }

                _isLoading.postValue(false) // Set isLoading to false after fetching data
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Log.e(TAG, "Fetch followers failed. Error: ${t.message}")
                // Handle network error here
                _isLoading.postValue(false) // Set isLoading to false if there's an error
            }
        })
    }

    fun fetchDataFollowers(username: String) {
        _isLoading.postValue(true) // Set isLoading to true while fetching data

        val apiService = ApiConfig.getApiService()
        val call = apiService.getFollowers(username)

        call.enqueue(object : Callback<List<ItemFollowers>> { // Use List<ItemFollowers> as the callback type
            override fun onResponse(
                call: Call<List<ItemFollowers>>,
                response: Response<List<ItemFollowers>>
            ) {
                if (response.isSuccessful) {
                    val followersList = response.body() ?: emptyList()
                    _listFolower.postValue(followersList)
                    Log.i(TAG, "Fetch followers successful. Count: ${followersList.size}")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }

                _isLoading.postValue(false) // Set isLoading to false after fetching data
            }

            override fun onFailure(call: Call<List<ItemFollowers>>, t: Throwable) {
                Log.e(TAG, "Fetch followers failed. Error: ${t.message}")

                // Handle network error here
                _isLoading.postValue(false) // Set isLoading to false if there's an error
            }
        })
    }

    fun fetchDataFollowing(username: String) {
        _isLoading.postValue(true) // Set isLoading to true while fetching data

        val apiService = ApiConfig.getApiService()
        val call = apiService.getFollowing(username)

        call.enqueue(object : Callback<List<FollowingResponseItem>> { // Use List<ItemFollowers> as the callback type
            override fun onResponse(
                call: Call<List<FollowingResponseItem>>,
                response: Response<List<FollowingResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val followingList = response.body() ?: emptyList()
                    _listFollowing.postValue(followingList)
                    Log.i(TAG, "Fetch followers successful. Count: ${followingList.size}")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }

                _isLoading.postValue(false) // Set isLoading to false after fetching data
            }

            override fun onFailure(call: Call<List<FollowingResponseItem>>, t: Throwable) {
                Log.e(TAG, "Fetch followers failed. Error: ${t.message}")

                // Handle network error here
                _isLoading.postValue(false) // Set isLoading to false if there's an error
            }
        })
    }


}
