package com.example.aplikasigithubuser.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.response.DetailResponse
import com.example.aplikasigithubuser.ui.adapter.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {
    private lateinit var user: DetailResponse
    private var TAB_TITLES: Array<String> = emptyArray() // Initialize with empty array

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val username = arguments?.getString(ARG_USERNAME)

        val rootView = inflater.inflate(R.layout.fragment_detail, container, false)
        val progressBarDetail = rootView.findViewById<ProgressBar>(R.id.progressBarDetail)
        progressBarDetail.visibility = View.VISIBLE

        if (username != null) {
            val service = ApiConfig.getApiService()
            val call = service.getUser(username)

            call.enqueue(object : Callback<DetailResponse> {
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    progressBarDetail.visibility = View.GONE

                    if (response.isSuccessful) {
                        user = response.body() ?: return

                        // Update the TAB_TITLES array
                        TAB_TITLES = arrayOf(
                            "${user.followers} ${getString(R.string.tab_text_1)}",
                            "${user.following} ${getString(R.string.tab_text_2)}",
                            // Add more strings as needed
                        )

                        // Lakukan sesuatu dengan data pengguna yang diperoleh
                        val tvName = rootView.findViewById<TextView>(R.id.tv_name)
                        val ivImageDetail = rootView.findViewById<ImageView>(R.id.iv_image_detail)
                        Glide.with(this@DetailFragment)
                            .load(user.avatarUrl)
                            .into(ivImageDetail)
                        tvName.text = user.login ?: ""


                        // Now, set up ViewPager and TabLayout here
                        val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity(), TAB_TITLES)
                        val viewPager: ViewPager2 = rootView.findViewById(R.id.view_pager)
                        viewPager.adapter = sectionsPagerAdapter
                        val tabs: TabLayout = rootView.findViewById(R.id.tabs)
                        TabLayoutMediator(tabs, viewPager) { tab, position ->
                            tab.text = TAB_TITLES[position]
                        }.attach()
                        (requireActivity() as AppCompatActivity).supportActionBar?.elevation = 0f

                    } else {
                        // Handle unsuccessful response
                    }
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    progressBarDetail.visibility = View.GONE
                    // Handle network failure
                }
            })
        }

        return rootView
    }
}
