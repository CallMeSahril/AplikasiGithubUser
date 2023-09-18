package com.example.aplikasigithubuser.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.data.response.FollowersResponseItem
import com.example.aplikasigithubuser.data.response.Itemsitem
import com.example.aplikasigithubuser.databinding.ItemUserCardBinding
import com.example.aplikasigithubuser.databinding.ItemUserFollowersBinding

class FollowersAdapter(private val onItemClickListener: OnItemClickListener) :
    androidx.recyclerview.widget.ListAdapter<FollowersResponseItem, FollowersAdapter.MyViewHolder>(DIFF_CALLBACK) {

    // Interface untuk menangani klik item
    interface OnItemClickListener {
        fun onItemClick(item: FollowersResponseItem)
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

        fun bind(review: FollowersResponseItem) {
            binding.tvFullNameFollowers.text = "${review.login}"
//            Glide.with(binding.root.context)
//                .load(review.avatarUrl)
//                .into(binding.ivImage)

            Log.i("Gambar", "${review.avatarUrl}")
            Log.i("Gambar", "${review.login}")

            // Tambahkan kode untuk menangani klik item di sini
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(review)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FollowersResponseItem>() {
            override fun areItemsTheSame(oldItem: FollowersResponseItem, newItem: FollowersResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: FollowersResponseItem, newItem: FollowersResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}