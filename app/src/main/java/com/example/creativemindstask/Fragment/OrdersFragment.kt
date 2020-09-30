package com.example.creativemindstask.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.creativemindstask.R
import com.example.creativemindstask.Adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout


class OrdersFragment (val flag:Int): Fragment() {
    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        viewPager = view.findViewById(R.id.pagerBooking)
        tabLayout = view.findViewById(R.id.sliding_tabs)

        tabLayout.addTab(tabLayout.newTab().setText("تم الالغاء"))
        tabLayout.addTab(tabLayout.newTab().setText("تم القبول"))
        tabLayout.addTab(tabLayout.newTab().setText("قيد الانتظار"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        val viewPagerAdapter =
            ViewPagerAdapter(
                childFragmentManager,
                flag
            )
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = 3
        return view
    }

}