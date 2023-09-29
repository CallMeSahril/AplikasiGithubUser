package com.example.aplikasigithubuser.data.local.entity

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favo: Favorite)
    @Delete
    fun delete(favo: Favorite)

    @Query("SELECT * from favorite ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Favorite>>
}