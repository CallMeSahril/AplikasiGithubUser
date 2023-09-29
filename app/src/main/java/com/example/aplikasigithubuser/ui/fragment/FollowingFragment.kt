package com.example.aplikasigithubuser.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.response.FollowingResponseItem
import com.example.aplikasigithubuser.data.response.ItemFollowers
import com.example.aplikasigithubuser.databinding.FragmentFollowingBinding
import com.example.aplikasigithubuser.ui.adapter.FollowersAdapter
import com.example.aplikasigithubuser.ui.adapter.FollowingAdapter
import com.example.aplikasigithubuser.ui.fragment.DetailFragment
import com.example.aplikasigithubuser.ui.viewmodel.MainViewModel


class FollowingFragment (val username :String) : Fragment() ,FollowingAdapter.OnItemClickListener {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("com.example.aplikasigithubuser.ui.fragment.FollowerFragment", "onCreateView called")

        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val rootView = binding.root

        return rootView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchDataFollowing(username)



//        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_follower)
//        val adapter = FollowersAdapter(this)

        // Mengatur LinearLayoutManager
        val orientation = resources.configuration.orientation

        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(context, 2)
        } else {
            LinearLayoutManager(context)
        }
        binding.rvFollowing.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFollowing.addItemDecoration(itemDecoration)


        viewModel.listReviewFollowing.observe(viewLifecycleOwner) { consumerReviews ->
            Log.i("com.example.aplikasigithubuser.ui.fragment.FollowerFragment", "Data changed: ${consumerReviews.size} items")
            setReviewData(consumerReviews)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }


    }
    private fun setReviewData(consumerReviews: List<FollowingResponseItem>) {
        val adapter = FollowingAdapter(this)
        adapter.submitList(consumerReviews)
        binding.rvFollowing.adapter = adapter

        if (consumerReviews.isEmpty()) {
            binding.emptyDataText.visibility = View.VISIBLE
        } else {
            binding.emptyDataText.visibility = View.GONE
        }

    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: FollowingResponseItem) {
        val username = item.login
        Log.i("Username", "onItemClick Following: ${item.login} ")
        // Membuat instance fragment detail dan mengirim data
        val detailFragment = DetailFragment.newInstance("$username")

        // Mengganti fragment saat item diklik
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }




}