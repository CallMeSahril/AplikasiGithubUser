package com.example.aplikasigithubuser.ui.fragment

import android.content.res.Configuration
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.response.Itemsitem
import com.example.aplikasigithubuser.databinding.FragmentHomeBinding
import com.example.aplikasigithubuser.ui.viewmodel.MainViewModel
import com.example.aplikasigithubuser.ui.adapter.UserAdapter

class HomeFragment : Fragment(), UserAdapter.OnItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = UserAdapter(this)

        // Mengatur LinearLayoutManager

        val orientation = resources.configuration.orientation

        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(context, 2)
        } else {
            LinearLayoutManager(context)
        }
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionId, event ->
                val query = searchView.text.toString().trim()
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (query.isEmpty()) {
                        // Jika pengguna tidak memasukkan teks, panggil metode fetchDataFromApi dengan nilai default "sahril"
                        viewModel.fetchDataFromApi("sahril")
                    } else {
                        // Jika pengguna memasukkan teks, panggil metode fetchDataFromApi dengan nilai query
                        viewModel.fetchDataFromApi(query)
                    }

                    // Lakukan tindakan lain yang Anda perlukan saat pengguna menekan tombol "Cari"
                    searchBar.text = searchView.text
                    searchView.hide()
                    Toast.makeText(context, searchView.text, Toast.LENGTH_SHORT).show()

                    true
                } else {
                    false
                }
            }


        }

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

        return rootView
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
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.frame_container, detailFragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

}
