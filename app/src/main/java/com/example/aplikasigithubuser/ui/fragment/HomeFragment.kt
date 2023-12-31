package com.example.aplikasigithubuser.ui.fragment

import android.content.Intent
import android.content.res.Configuration
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.remote.response.Itemsitem

import com.example.aplikasigithubuser.databinding.FragmentHomeBinding
import com.example.aplikasigithubuser.ui.activity.FavoriteActivity
import com.example.aplikasigithubuser.ui.activity.SettingActivity
import com.example.aplikasigithubuser.ui.viewmodel.ApiViewModel
import com.example.aplikasigithubuser.ui.adapter.UserAdapter

class HomeFragment : Fragment(), UserAdapter.OnItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ApiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = UserAdapter(this)


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
                        viewModel.fetchDataFromApi("sahril")
                    } else {
                        viewModel.fetchDataFromApi(query)
                    }

                    searchBar.text = searchView.text
                    searchView.hide()
                    Toast.makeText(context, searchView.text, Toast.LENGTH_SHORT).show()

                    true
                } else {
                    false
                }
            }

            setHasOptionsMenu(true)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

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

        val detailFragment = DetailFragment.newInstance("$username")

        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.frame_container, detailFragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val themeMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        val settingIcon = if (themeMode == Configuration.UI_MODE_NIGHT_YES) {
            R.drawable.ic_settings_white
        } else {
            R.drawable.ic_settings
        }
        val favoriteIcon = if (themeMode == Configuration.UI_MODE_NIGHT_YES) {
            R.drawable.ic_favorite_white
        } else {
            R.drawable.ic_favorite
        }

        menu.findItem(R.id.setting).setIcon(settingIcon)
        menu.findItem(R.id.favorite).setIcon(favoriteIcon)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting -> {
                val intent = Intent(context, SettingActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.favorite -> {
                val intent = Intent(context, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
