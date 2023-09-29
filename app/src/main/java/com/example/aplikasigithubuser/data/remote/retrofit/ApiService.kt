package com.example.aplikasigithubuser.data.remote.retrofit

import com.example.aplikasigithubuser.data.remote.response.DetailResponse
import com.example.aplikasigithubuser.data.remote.response.FollowingResponseItem
import com.example.aplikasigithubuser.data.remote.response.GithubResponse
import com.example.aplikasigithubuser.data.remote.response.ItemFollowers

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun searchUsers(
        @Query("q") username: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<DetailResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemFollowers>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<FollowingResponseItem>>
}
