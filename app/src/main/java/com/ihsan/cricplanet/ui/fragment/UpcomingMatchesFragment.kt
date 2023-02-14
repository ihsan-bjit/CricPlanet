package com.ihsan.cricplanet.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.MatchAdapter
import com.ihsan.cricplanet.databinding.FragmentUpcomingMatchesBinding
import com.ihsan.cricplanet.viewmodel.CricViewModel

class UpcomingMatchesFragment : Fragment() {
    private lateinit var binding:FragmentUpcomingMatchesBinding
    private val viewModel: CricViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentUpcomingMatchesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("cricUpcomingMatch", "onViewCreated: ")
        recyclerView=binding.recyclerviewMatches
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        viewModel.getUpcomingFixturesApi().observe(viewLifecycleOwner) {
            Log.d("cricTeam", "onViewCreated Upcoming Match Fragment: $it")
            recyclerView.adapter= MatchAdapter(it)
            if (it.isEmpty()) {
                Log.d("cricTeam", "onViewCreated with empty roomData: APi Call ")
                viewModel.getTeams()
            }
        }
    }
}