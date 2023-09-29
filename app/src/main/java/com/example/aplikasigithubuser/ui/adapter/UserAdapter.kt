package com.example.aplikasigithubuser.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.data.remote.response.Itemsitem
import com.example.aplikasigithubuser.databinding.ItemUserCardBinding

class UserAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<Itemsitem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    // Interface untuk menangani klik item
    interface OnItemClickListener {
        fun onItemClick(item: Itemsitem)
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

        fun bind(review: Itemsitem) {
            binding.tvFullName.text = "${review.login}"
            binding.tvUrl.text = review.htmlUrl
            Glide.with(binding.root.context)
                .load(review.avatarUrl)
                .into(binding.ivImage)

            Log.i(TAG, "bind: ${review.login}")

            // Tambahkan kode untuk menangani klik item di sini
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(review)
            }
        }
    }

    companion object {
        const val TAG = "UserAdapter"
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Itemsitem>() {
            override fun areItemsTheSame(oldItem: Itemsitem, newItem: Itemsitem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Itemsitem, newItem: Itemsitem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
