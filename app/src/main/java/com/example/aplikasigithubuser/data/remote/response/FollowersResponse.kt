package com.example.aplikasigithubuser.data.remote.response

import com.google.gson.annotations.SerializedName

data class FollowersResponse(

	@field:SerializedName("SearchResponse")
	val itemFollowers: List<ItemFollowers?>? = null
)

data class ItemFollowers(


	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	)
