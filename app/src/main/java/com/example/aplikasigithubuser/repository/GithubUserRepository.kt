package com.example.aplikasigithubuser.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.aplikasigithubuser.data.local.database.GithubUser
import com.example.aplikasigithubuser.data.local.database.GithubUserDao
import com.example.aplikasigithubuser.data.local.database.GithubUserRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GithubUserRepository(application: Application) {
    private val mNotesDao: GithubUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = GithubUserRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()



    }
    fun getAllNotes(): LiveData<List<GithubUser>> = mNotesDao.getAllNotes()
    fun insert(githubUser: GithubUser) {
        executorService.execute { mNotesDao.insert(githubUser) }
    }
    fun delete(githubUser: GithubUser) {
        executorService.execute { mNotesDao.delete(githubUser) }
    }
    fun update(githubUser: GithubUser) {
        executorService.execute { mNotesDao.update(githubUser) }
    }

    fun checkUser(username: String): LiveData<GithubUser> {
        return mNotesDao.getFavoriteUserByUsername(username)
    }


}
