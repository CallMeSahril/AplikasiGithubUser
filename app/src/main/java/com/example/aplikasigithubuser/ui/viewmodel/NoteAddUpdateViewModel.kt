package com.example.aplikasigithubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.aplikasigithubuser.data.local.database.Note
import com.example.aplikasigithubuser.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel() {

     var mNoteRepository: NoteRepository = NoteRepository(application)
    fun insert(note: Note) {
        mNoteRepository.insert(note)
    }
    fun update(note: Note) {
        mNoteRepository.update(note)
    }
    fun delete(note: Note) {
        mNoteRepository.delete(note)
    }
}
