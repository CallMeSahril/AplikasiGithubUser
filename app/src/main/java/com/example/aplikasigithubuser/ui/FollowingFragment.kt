package com.example.aplikasigithubuser.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.response.Itemsitem
import com.example.aplikasigithubuser.databinding.FragmentFollowerBinding
import com.example.aplikasigithubuser.ui.adapter.UserAdapter
import com.example.aplikasigithubuser.ui.fragment.DetailFragment
import com.example.aplikasigithubuser.ui.viewmodel.MainViewModel


class FollowingFragment : Fragment() ,UserAdapter.OnItemClickListener {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("FollowerFragment", "onCreateView called")

        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        val rootView = binding.root

        return rootView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tambahkan kode khusus yang ingin Anda jalankan saat onViewCreated di sini
        // Contoh: menampilkan pesan Toast saat fragment dibuat
        Toast.makeText(requireContext(), "Fragment Follower Created", Toast.LENGTH_SHORT).show()

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_follower)
        val adapter = UserAdapter(this)

        // Mengatur LinearLayoutManager
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter

        // Observe LiveData for isLoading
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                // Tampilkan ProgressBar jika isLoading adalah true
                binding.progressBar.visibility = View.VISIBLE
            } else {
                // Sembunyikan ProgressBar jika isLoading adalah false
                binding.progressBar.visibility = View.GONE
            }
        }

        // Observe LiveData here and update the adapter data
        viewModel.listReview.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: Itemsitem) {
        val username = item.login

        // Membuat instance fragment detail dan mengirim data
        val detailFragment = DetailFragment.newInstance("$username")

        // Mengganti fragment saat item diklik
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }




}