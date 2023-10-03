package com.example.aplikasigithubuser.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.data.remote.response.ItemFollowers
import com.example.aplikasigithubuser.databinding.ItemUserCardBinding

class FollowersAdapter(private val onItemClickListener: OnItemClickListener) :
    androidx.recyclerview.widget.ListAdapter<ItemFollowers, FollowersAdapter.MyViewHolder>(
        DIFF_CALLBACK
    ) {

    interface OnItemClickListener {
        fun onItemClick(item: ItemFollowers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemUserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    class MyViewHolder(
        private val binding: ItemUserCardBinding,
        private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(review: ItemFollowers) {
            binding.tvFullName.text = "${review.login}"
            binding.tvUrl.text = review.htmlUrl

            Glide.with(binding.root.context)
                .load(review.avatarUrl)
                .into(binding.ivImage)


            Log.i(TAG, "${review.login}")

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

            override fun areContentsTheSame(
                oldItem: ItemFollowers,
                newItem: ItemFollowers
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}