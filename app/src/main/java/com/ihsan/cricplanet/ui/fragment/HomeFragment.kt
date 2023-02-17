package com.ihsan.cricplanet.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
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

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: CricViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    lateinit var viewPagerAdapter: LiveMatchSliderAdapter
    lateinit var indicator: CircleIndicator
    private val DELAY_MS: Long = 2000
    private val PERIOD_MS: Long = 4000
    private val handler = Handler()
    private var update:Runnable?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        stopAutoSlide()
        startAutoSlide()
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Live Card Slider
        val viewPager=binding.viewpager
        viewModel.getUpcomingFixturesApi()
        viewModel.upcomingMatchFixture.observe(viewLifecycleOwner){
            Log.d("cricHome", "onViewCreatedHomeSlider: $it")
            stopAutoSlide()
            update=null
            viewPagerAdapter = LiveMatchSliderAdapter(requireContext(), it as ArrayList)
            viewPager.adapter = viewPagerAdapter
            indicator = binding.indicator
            indicator.setViewPager(viewPager)
            update = Runnable {
                var currentPage = viewPager.currentItem
                val totalPages = viewPager.adapter?.count ?: 0
                currentPage = (currentPage + 1) % totalPages
                viewPager.currentItem = currentPage
                startAutoSlide()
            }
            startAutoSlide()
        }

        //Recycler view
        recyclerView=binding.recyclerviewToday
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        //Get Data From API
        viewModel.getTodayFixturesApi()
        viewModel.todayFixture.observe(viewLifecycleOwner) {
            Log.d("cricTeam", "onViewCreated MatchFixture: $it")
            recyclerView.adapter= MatchAdapter(it)
        }
    }

    override fun onPause() {
        super.onPause()
        stopAutoSlide()
    }
    fun startAutoSlide() {
        if (update!=null){
            handler.postDelayed(update!!, PERIOD_MS)
        }
    }

    fun stopAutoSlide() {
        if (update!=null){
            handler.removeCallbacks(update!!)
        }
    }
}