package com.example.aplikasigithubuser.ui.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.aplikasigithubuser.data.local.database.GithubUser
import com.example.aplikasigithubuser.repository.GithubUserRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

import org.mockito.MockitoAnnotations

class GithubUserAddDeleteViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var applicationContext: Application

    @Mock
    private lateinit var githubUserRepository: GithubUserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(applicationContext.applicationContext).thenReturn(applicationContext)

        Mockito.`when`(githubUserRepository.checkUser("username"))
            .thenReturn(MutableLiveData(GithubUser(32131, "name", "avatarUrl")))
    }

    @Test
    fun insert() {
        val githubUser = GithubUser(32131, "name", "avatarUrl")

        synchronized(githubUserRepository) {
            githubUserRepository.insert(githubUser)
        }
        Mockito.verify(githubUserRepository).insert(githubUser)
    }


}
