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
import com.example.aplikasigithubuser.data.remote.response.FollowingResponseItem

import com.example.aplikasigithubuser.databinding.FragmentFollowingBinding
import com.example.aplikasigithubuser.ui.adapter.FollowingAdapter
import com.example.aplikasigithubuser.ui.viewmodel.ApiViewModel


class FollowingFragment(val username: String) : Fragment(), FollowingAdapter.OnItemClickListener {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ApiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchDataFollowing(username)


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
        val detailFragment = DetailFragment.newInstance("$username")

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}