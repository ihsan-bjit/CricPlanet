package com.ihsan.cricplanet.ui.fragment.viewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.ihsan.cricplanet.adapter.viewpager.TabMatchDetailAdapter
import com.ihsan.cricplanet.databinding.FragmentMatchDetailTabLayoutBinding
import com.ihsan.cricplanet.viewmodel.CricViewModel

class MatchDetailTabLayoutFragment : Fragment() {
    private val args: MatchDetailTabLayoutFragmentArgs by navArgs()
    private lateinit var binding: FragmentMatchDetailTabLayoutBinding
    private val viewmodel: CricViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMatchDetailTabLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Tab layout
        val tabLayout = binding.tabLayoutMatchDetails
        val viewPage = binding.viewPager2MatchDetails
        Log.d("cricMatchDetail", "Match Detail fragment onViewCreated: ${args.matchId}")
        viewmodel.getFixturesByIdApi(args.matchId)

        //Assigning match Adapter
        val tabMatchDetailAdapter =
            TabMatchDetailAdapter(childFragmentManager, lifecycle, args.matchId)
        viewPage.adapter = tabMatchDetailAdapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            tab.text = TabMatchDetailAdapter.listMatchDetailTab[position].category
        }.attach()

        //Auto Hide Top view
        var mBottomViewVisible = true
        val scrollView = binding.detailsMatchScrollView
        val mBottomView = binding.topInfo
        scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY && mBottomViewVisible) {
                mBottomView?.animate()?.translationY(mBottomView?.height?.toFloat() ?: 0f)?.start()
                mBottomViewVisible = true
            } else if (scrollY < oldScrollY && !mBottomViewVisible) {
                mBottomView?.animate()?.translationY(0f)?.start()
                mBottomViewVisible = false
            }
        }

    }
}