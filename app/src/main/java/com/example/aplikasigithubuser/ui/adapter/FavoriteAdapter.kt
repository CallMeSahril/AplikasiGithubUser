//package com.example.aplikasigithubuser.ui.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.aplikasigithubuser.data.local.entity.Favorite
//import com.example.aplikasigithubuser.databinding.ItemUserCardBinding
//
//class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
//     val listNotes = ArrayList<Favorite>()
//    fun setListNotes(listNotes: List<Favorite>) {
//        val diffCallback = FavoriteDiffCallback(this.listNotes, listNotes)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        this.listNotes.clear()
//        this.listNotes.addAll(listNotes)
//        diffResult.dispatchUpdatesTo(this)
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
//        val binding = ItemUserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return FavoriteViewHolder(binding)
//    }
//    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
//        holder.bind(listNotes[position])
//    }
//    override fun getItemCount(): Int {
//        return listNotes.size
//    }
//    inner class FavoriteViewHolder(private val binding: ItemUserCardBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(note: Favorite) {
//            with(binding) {
//                tvFullName.text = note.username
//                Glide.with(binding.root.context)
//                    .load(note.avatarUrl)
//                    .into(binding.ivImage)
//
//
//            }
//        }
//    }
//}