package com.example.ecommerceappproject.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentList: MutableList<Fragment> =
        ArrayList()
    private val fragmentTitleList: MutableList<String> =
        ArrayList()

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
        fragmentTitleList.add(fragment.javaClass.simpleName)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}