package com.ihsan.cricplanet.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.ui.fragment.*

class TabMatchAdapter (manager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(manager,lifecycle) {
    companion object{
        val matchListTab = listOf(
            Tab(UpcomingMatchesFragment(), "Upcoming"),
            Tab(RecentMatchesFragment(), "Recent"),
            Tab(MatchesFragment(), "ALL")
        )
    }
    override fun getItemCount(): Int {
        return  matchListTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return matchListTab[position].fragment
    }
}