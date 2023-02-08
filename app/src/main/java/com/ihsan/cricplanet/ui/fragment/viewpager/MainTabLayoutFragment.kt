package com.ihsan.cricplanet.ui.fragment.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.TabAdapter
import com.ihsan.cricplanet.adapter.TabAdapter.Companion.listTab
import com.ihsan.cricplanet.databinding.FragmentMainTabLayoutBinding

class MainTabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentMainTabLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainTabLayoutBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Tab layout
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPage = view.findViewById<ViewPager2>(R.id.view_pager_2)
        val tabAdapter = TabAdapter(childFragmentManager, lifecycle)
        viewPage.adapter = tabAdapter

        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            tab.text = listTab[position].category
        }.attach()
    }
}