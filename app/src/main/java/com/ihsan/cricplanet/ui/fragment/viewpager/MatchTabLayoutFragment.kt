package com.ihsan.cricplanet.ui.fragment.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.TabAdapter
import com.ihsan.cricplanet.adapter.TabMatchAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchTabLayoutBinding

class MatchTabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentMatchTabLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchTabLayoutBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Tab layout
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout_match)
        val viewPage = view.findViewById<ViewPager2>(R.id.view_pager_2_match)
        val tabMatchAdapter = TabMatchAdapter(childFragmentManager, lifecycle)
        viewPage.adapter = tabMatchAdapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            tab.text = TabMatchAdapter.matchListTab[position].category
        }.attach()
    }
}