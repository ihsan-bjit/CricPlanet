package com.ihsan.cricplanet.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.ui.fragment.HomeFragment
import com.ihsan.cricplanet.ui.fragment.LiveFragment

class TabAdapter(manager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(manager,lifecycle) {
    companion object{
        val listTab = listOf(
            Tab(HomeFragment(), "Home"),
            Tab(LiveFragment(), "Live")
        )
    }
    override fun getItemCount(): Int {
        return  listTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return listTab[position].fragment
    }
}