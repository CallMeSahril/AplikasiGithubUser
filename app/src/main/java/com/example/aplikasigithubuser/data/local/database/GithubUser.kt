package com.example.aplikasigithubuser.data.local.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class GithubUser(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "name_github_user")
    var nameGithubUser: String? = null,
    @ColumnInfo(name = "url_github_user")
    var urlGithubUser: String? = null,
    @ColumnInfo(name = "image_github_user")
    var imageGithubUser: String? = null,

    ) : Parcelable