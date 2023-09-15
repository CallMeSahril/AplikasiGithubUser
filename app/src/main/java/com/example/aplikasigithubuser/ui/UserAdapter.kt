package com.example.aplikasigithubuser.ui

import HomeFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.data.response.ItemsItem
import com.example.aplikasigithubuser.databinding.ItemUserCardBinding
import kotlin.math.log

class UserAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<ItemsItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    // Interface untuk menangani klik item
    interface OnItemClickListener {
        fun onItemClick(item: ItemsItem)
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

        fun bind(review: ItemsItem) {
            binding.tvFullName.text = "${review.login}"
            Glide.with(binding.root.context)
                .load(review.avatarUrl)
                .into(binding.ivImage)

            Log.i("IMAGE", "${review.avatarUrl}")

            // Tambahkan kode untuk menangani klik item di sini
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(review)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
