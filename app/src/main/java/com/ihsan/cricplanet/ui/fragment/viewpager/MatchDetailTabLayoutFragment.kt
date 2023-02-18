package com.ihsan.cricplanet.ui.fragment.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.adapter.viewpager.TabMatchDetailAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchDetailTabLayoutBinding

class MatchDetailTabLayoutFragment : Fragment() {
    private val args: MatchDetailTabLayoutFragmentArgs by navArgs()
    private lateinit var binding: FragmentMatchDetailTabLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMatchDetailTabLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Tab layout
        val tabLayout = binding.tabLayoutMatchDetails
        val viewPage = binding.viewPager2MatchDetails
        Log.d("cricMatchDetail", "Match Detail fragment onViewCreated: ${args.matchId}")
        val tabMatchDetailAdapter = TabMatchDetailAdapter(childFragmentManager, lifecycle,args.matchId)
        //tabMatchDetailAdapter.setMatchId(args.matchId)
        viewPage.adapter = tabMatchDetailAdapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            tab.text = TabMatchDetailAdapter.listMatchDetailTab[position].category
        }.attach()
    }
}