package com.ihsan.cricplanet.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.LiveMatchSliderAdapter
import com.ihsan.cricplanet.adapter.MatchAdapter
import com.ihsan.cricplanet.databinding.FragmentHomeBinding
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForCard
import com.ihsan.cricplanet.model.slider.FixtureIncludeForCardSlider
import com.ihsan.cricplanet.viewmodel.CricViewModel
import me.relex.circleindicator.CircleIndicator

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: CricViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    lateinit var viewPagerAdapter: LiveMatchSliderAdapter
    lateinit var indicator: CircleIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Live Card Slider
        val viewpager=binding.viewpager
        viewModel.getLiveFixturesApi()
        viewModel.liveFixture.observe(viewLifecycleOwner){
            Log.d("cricHome", "onViewCreatedHomeSlider: $it")
            viewPagerAdapter = LiveMatchSliderAdapter(requireContext(), it as ArrayList)
            viewpager.adapter = viewPagerAdapter
            indicator = binding.indicator
            indicator.setViewPager(viewpager)
        }

        //Recycler view
        recyclerView=binding.recyclerviewToday
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.setOnTouchListener { v, event ->
            false
        }
        //Get Data From API
        viewModel.getTodayFixturesApi()
        viewModel.todayFixture.observe(viewLifecycleOwner) {
            Log.d("cricTeam", "onViewCreated MatchFixture: $it")
            recyclerView.adapter= MatchAdapter(it)
        }
    }
}