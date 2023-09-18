//package com.example.aplikasigithubuser.ui.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.aplikasigithubuser.data.response.FollowersResponse
//import com.example.aplikasigithubuser.data.response.FollowersResponseItem
//import com.example.aplikasigithubuser.data.response.GithubResponse
//import com.example.aplikasigithubuser.data.response.Itemsitem
//import com.example.aplikasigithubuser.data.retrofit.ApiConfig
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class FollowersViewModel : ViewModel()  {
//    private val _listFolower = MutableLiveData<List<FollowersResponseItem>>()
//    val listReviewFolower: LiveData<List<FollowersResponseItem>> = _listFolower
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    companion object {
//        private const val TAG = "FollowersViewModel"
//    }
//    init {
//
//        fetchDataFollowers()
//    }
//
//
//    fun fetchDataFollowers() {
//        _isLoading.postValue(true) // Set isLoading to true while fetching data
//
//        val apiService = ApiConfig.getApiService()
//        val call = apiService.getFollowers("CallMeSahril") // Menggunakan parameter username yang diterima
//
//        call.enqueue(object : Callback<FollowersResponse> {
//            override fun onResponse(
//                call: Call<FollowersResponse>,
//                response: Response<FollowersResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val followersResponse = response.body()
//                    val itemsListFollower = followersResponse?.followersResponse ?: emptyList()
//                    _listFolower.postValue(itemsListFollower as List<FollowersResponseItem>?)
//
//                } else {
//
//                    // Handle API error here
//                }
//
//                _isLoading.postValue(false) // Set isLoading to false after fetching data
//            }
//
//            override fun onFailure(call: Call<FollowersResponse>, t: Throwable) {
//                // Handle network error here
//                _isLoading.postValue(false) // Set isLoading to false if there's an error
//            }
//        })
//    }
//
//
//
//}