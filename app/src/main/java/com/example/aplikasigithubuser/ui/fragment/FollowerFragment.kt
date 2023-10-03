package com.example.aplikasigithubuser.ui.fragment

import android.content.res.Configuration
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.remote.response.ItemFollowers

import com.example.aplikasigithubuser.databinding.FragmentFollowerBinding
import com.example.aplikasigithubuser.ui.adapter.FollowersAdapter
import com.example.aplikasigithubuser.ui.viewmodel.ApiViewModel

class FollowerFragment(val username: String) : Fragment(), FollowersAdapter.OnItemClickListener {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ApiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("com.example.aplikasigithubuser.ui.fragment.FollowerFragment", "onCreateView called")

        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        val rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.fetchDataFollowers(username)

        val orientation = resources.configuration.orientation

        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(context, 2)
        } else {
            LinearLayoutManager(context)
        }

        binding.rvFollower.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFollower.addItemDecoration(itemDecoration)


        viewModel.listReviewFolower.observe(viewLifecycleOwner) { consumerReviews ->
            Log.i(
                "com.example.aplikasigithubuser.ui.fragment.FollowerFragment",
                "Data changed: ${consumerReviews.size} items"
            )
            setReviewData(consumerReviews)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }


    }

    private fun setReviewData(consumerReviews: List<ItemFollowers>) {
        val adapter = FollowersAdapter(this)
        adapter.submitList(consumerReviews)
        binding.rvFollower.adapter = adapter

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

    override fun onItemClick(item: ItemFollowers) {
        val username = item.login
        Log.i("Username", "onItemClick Follow: ${item.login} ")
        val detailFragment = DetailFragment.newInstance("$username")

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()


    }
}
