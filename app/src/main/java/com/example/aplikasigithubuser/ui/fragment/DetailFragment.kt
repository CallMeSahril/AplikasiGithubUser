package com.example.aplikasigithubuser.ui.fragment

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.local.database.GithubUser
import com.example.aplikasigithubuser.data.remote.response.DetailResponse
import com.example.aplikasigithubuser.databinding.FragmentDetailBinding
import com.example.aplikasigithubuser.databinding.FragmentHomeBinding
import com.example.aplikasigithubuser.ui.activity.MainActivity
import com.example.aplikasigithubuser.ui.viewmodel.GithubUserAddDeleteViewModel


import com.example.aplikasigithubuser.ui.adapter.SectionsPagerAdapter
import com.example.aplikasigithubuser.ui.adapter.UserAdapter
import com.example.aplikasigithubuser.ui.viewmodel.ApiViewModel
import com.example.aplikasigithubuser.ui.viewmodel.DetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {
    private var TAB_TITLES: Array<String> = emptyArray() // Initialize with empty array
    private var isFavorite = false
    private val viewModel: DetailViewModel by viewModels()

    companion object {
        const val ARG_USERNAME = "username"

        fun newInstance(username: String): DetailFragment {
            val args = Bundle()
            args.putString(ARG_USERNAME, username)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val githubUserAddDeleteViewModel by lazy {
        GithubUserAddDeleteViewModel(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val username = arguments?.getString(ARG_USERNAME)

        val rootView = inflater.inflate(R.layout.fragment_detail, container, false)
        val progressBarDetail = rootView.findViewById<ProgressBar>(R.id.progressBarDetail)
        progressBarDetail.visibility = View.GONE


        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progressBarDetail.visibility = View.VISIBLE
            } else {
                progressBarDetail.visibility = View.GONE
            }
        }
        val isDarkTheme =
            resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

        val backButton = rootView.findViewById<ImageButton>(R.id.imageButton)
        backButton.setImageResource(
            if (isDarkTheme) {
                R.drawable.baseline_arrow_back_ios_24
            } else {
                R.drawable.baseline_arrow_back_ios_24_black
            }
        )
        backButton.setOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))

        }

        if (username != null) {
            viewModel.fetchGetUser(username)



            viewModel.GetUser.observe(viewLifecycleOwner) { items ->

                TAB_TITLES = arrayOf(
                    "${items.followers} ${getString(R.string.tab_text_1)}",
                    "${items.following} ${getString(R.string.tab_text_2)}",
                )
                val tvName = rootView.findViewById<TextView>(R.id.tv_name)
                val tvUserName = rootView.findViewById<TextView>(R.id.tv_name_user)
                val ivImageDetail = rootView.findViewById<ImageView>(R.id.iv_image_detail)

                val tvEmail = rootView.findViewById<TextView>(R.id.tv_email)
                val tvRepositori = rootView.findViewById<TextView>(R.id.tv_repositori)

                Glide.with(this@DetailFragment)
                    .load(items.avatarUrl)
                    .into(ivImageDetail)
                tvName.text = Html.fromHtml("<b>Name:</b> ${items.name}")
                tvUserName.text = Html.fromHtml("<b>UserName:</b> ${items.login}")


                tvEmail.text = Html.fromHtml("<b>Email:</b> ${items.email ?: "-"}")


                tvRepositori.text = Html.fromHtml("<b>Repositori:</b> ${items.publicRepos}")
                val fabAdd = rootView.findViewById<FloatingActionButton>(R.id.fab_add)
                githubUserAddDeleteViewModel.checkUser("${items.id}")
                    .observe(viewLifecycleOwner) { note ->
                        isFavorite = note != null
                        Log.i("DetailFragment", "onResponse: Note $isFavorite ")
                        Log.i("DetailFragment", "onResponse: Note ${items.avatarUrl} ")

                        if (isFavorite) {
                            fabAdd.setImageResource(R.drawable.ic_favorite)

                        } else {
                            fabAdd.setImageResource(R.drawable.ic_favorite_border)


                        }

                    }


                fabAdd.setOnClickListener {

                    val githubUser = GithubUser(
                        id = items.id!!,
                        nameGithubUser = items.login,
                        urlGithubUser = items.htmlUrl,
                        imageGithubUser = items.avatarUrl
                    )




                    Log.i("DetailFragment", "onResponse: $isFavorite n ${items.id} ")

                    if (isFavorite) {
                        githubUserAddDeleteViewModel.delete(githubUser)
                        fabAdd.setImageResource(R.drawable.ic_favorite_border)
                        showToast(getString(R.string.delete))
                    } else {
                        githubUserAddDeleteViewModel.insert(githubUser)
                        fabAdd.setImageResource(R.drawable.ic_favorite)
                        showToast(getString(R.string.added))
                    }
                }


                val sectionsPagerAdapter =
                    SectionsPagerAdapter(requireActivity(), TAB_TITLES, username)
                val viewPager: ViewPager2 = rootView.findViewById(R.id.view_pager)
                viewPager.adapter = sectionsPagerAdapter
                val tabs: TabLayout = rootView.findViewById(R.id.tabs)
                TabLayoutMediator(tabs, viewPager) { tab, position ->
                    tab.text = TAB_TITLES[position]
                }.attach()
                (requireActivity() as AppCompatActivity).supportActionBar?.elevation = 0f
            }
        }




        return rootView
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}

