package com.example.aplikasigithubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.aplikasigithubuser.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : Fragment() {
    private val TAB_TITLES = intArrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
    )
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

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        if (username != null) {
            // Lakukan sesuatu dengan username
            Toast.makeText(requireContext(), "Username: $username", Toast.LENGTH_SHORT).show()

            // Mendapatkan referensi ke TextView
            val tvName = view.findViewById<TextView>(R.id.tv_name)

            // Mengatur teks TextView dengan nilai username
            tvName.text = username
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up ViewPager and TabLayout here
        val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity())
        val viewPager: ViewPager2 = view.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = view.findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        (requireActivity() as AppCompatActivity).supportActionBar?.elevation = 0f
    }
}
