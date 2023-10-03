package com.example.aplikasigithubuser.ui.adapter

import com.example.aplikasigithubuser.ui.fragment.FollowerFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aplikasigithubuser.ui.fragment.FollowingFragment

class SectionsPagerAdapter(
    activity: FragmentActivity,
    private val tabTitles: Array<String>,
    val username: String
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerFragment(username)
            1 -> fragment = FollowingFragment(username)
        }
        return fragment as Fragment
    }
}
