package com.example.creativemindstask.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.creativemindstask.Fragment.*

class ViewPagerAdapter(supportFragmentManager: FragmentManager, val flag: Int) :
    FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {

        when (flag) {
            1 -> {
                return when (position) {
                    2 -> {
                        //  val homeFragment: HomeFragment = HomeFragment()
                        WaitingFragment()
                    }
                    1 -> {
                        AcceptedFragment()
                    }
                    0 -> {
                        // val movieFragment = MovieFragment()
                        CancelledFragment()
                    }
                    else -> getItem(position)
                }
            }
            2 -> {
                return when (position) {
                    2 -> {
                        //  val homeFragment: HomeFragment = HomeFragment()
                        WaitingOthersFragment()
                    }
                    1 -> {
                        AcceptedOthersFragment()
                    }
                    0 -> {
                        // val movieFragment = MovieFragment()
                        CancelledOthersFragment()
                    }
                    else -> getItem(position)
                }
            }
            else -> return getItem(position)
        }

    }

    override fun getCount(): Int {
        return 3
    }
}