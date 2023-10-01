package com.example.aplikasigithubuser.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GithubUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(githubUser: GithubUser)
    @Update
    fun update(githubUser: GithubUser)
    @Delete
    fun delete(githubUser: GithubUser)
    @Query("SELECT * from GithubUser ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<GithubUser>>

    @Query("SELECT * FROM GithubUser WHERE id = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<GithubUser>
}