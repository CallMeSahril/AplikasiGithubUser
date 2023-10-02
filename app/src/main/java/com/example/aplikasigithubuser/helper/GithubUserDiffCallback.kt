package com.example.aplikasigithubuser.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.aplikasigithubuser.data.local.database.GithubUser

class GithubUserDiffCallback(private val oldGithubUserList: List<GithubUser>, private val newGithubUserList: List<GithubUser>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldGithubUserList.size
    override fun getNewListSize(): Int = newGithubUserList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldGithubUserList[oldItemPosition].id == newGithubUserList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = oldGithubUserList[oldItemPosition]
        val newNote = newGithubUserList[newItemPosition]
        return oldNote.nameGithubUser == newNote.nameGithubUser && oldNote.urlGithubUser == newNote.urlGithubUser
    }
}
