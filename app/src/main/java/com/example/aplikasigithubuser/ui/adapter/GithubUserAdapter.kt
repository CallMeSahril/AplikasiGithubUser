package com.example.aplikasigithubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.data.local.database.GithubUser
import com.example.aplikasigithubuser.helper.GithubUserDiffCallback
import com.example.aplikasigithubuser.databinding.ItemUserCardBinding

class GithubUserAdapter(private val onItemClickListener: GithubUserAdapter.OnItemClickListener) :
    RecyclerView.Adapter<GithubUserAdapter.NoteViewHolder>() {
    private val listGithubUsers = ArrayList<GithubUser>()

    interface OnItemClickListener {
        fun onItemClick(item: GithubUser)
    }

    fun setListNotes(listGithubUsers: List<GithubUser>) {
        val diffCallback = GithubUserDiffCallback(this.listGithubUsers, listGithubUsers)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listGithubUsers.clear()
        this.listGithubUsers.addAll(listGithubUsers)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ItemUserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listGithubUsers[position])
    }

    override fun getItemCount(): Int {
        return listGithubUsers.size
    }

    inner class NoteViewHolder(
        private val binding: ItemUserCardBinding,
        private val onItemClickListener: GithubUserAdapter.OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: GithubUser) {
            with(binding) {
                binding.tvFullName.text = "${review.nameGithubUser}"
                binding.tvUrl.text = review.urlGithubUser
                Glide.with(binding.root.context)
                    .load(review.imageGithubUser)
                    .into(binding.ivImage)

                itemView.setOnClickListener {
                    onItemClickListener.onItemClick(review)
                }
            }
        }
    }
}