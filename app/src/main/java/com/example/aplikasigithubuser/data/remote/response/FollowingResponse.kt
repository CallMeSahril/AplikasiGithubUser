package com.example.aplikasigithubuser.data.remote.response

import com.google.gson.annotations.SerializedName

data class FollowingResponse(

    @field:SerializedName("FollowingResponse")
    val followingResponse: List<FollowingResponseItem?>? = null
)

data class FollowingResponseItem(

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("html_url")
    val htmlUrl: String? = null,

    )
