package com.example.aplikasigithubuser.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.data.response.ItemFollowers

import com.example.aplikasigithubuser.databinding.ItemUserFollowersBinding

class FollowersAdapter(private val onItemClickListener: OnItemClickListener) :
    androidx.recyclerview.widget.ListAdapter<ItemFollowers, FollowersAdapter.MyViewHolder>(DIFF_CALLBACK) {

    // Interface untuk menangani klik item
    interface OnItemClickListener {
        fun onItemClick(item: ItemFollowers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemUserFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    class MyViewHolder(
        private val binding: ItemUserFollowersBinding,
        private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(review: ItemFollowers) {
            binding.tvFullNameFollowers.text = "${review.login}"
            Glide.with(binding.root.context)
                .load(review.avatarUrl)
                .into(binding.ivImageFollowers)


            Log.i(TAG, "${review.login}")

            // Tambahkan kode untuk menangani klik item di sini
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(review)
            }
        }
    }

    companion object {
        const val TAG = "FollowersAdapter"
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemFollowers>() {
            override fun areItemsTheSame(oldItem: ItemFollowers, newItem: ItemFollowers): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemFollowers, newItem: ItemFollowers): Boolean {
                return oldItem == newItem
            }
        }
    }
}