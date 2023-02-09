package com.ihsan.cricplanet.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.databinding.FragmentTeamBinding
import com.ihsan.cricplanet.viewmodel.CricViewModel

class TeamFragment : Fragment() {
    private lateinit var binding: FragmentTeamBinding
    private val viewModel: CricViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentTeamBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTeams.observe(requireActivity()) {
            Log.d("cricTeam", "onViewCreated Team teamList: ${it.size}")
            if (it.isEmpty()) {
                Log.d("newsHome", "onViewCreated with empty roomData: APi Call ")
                viewModel.getTeams()
            }
        }
    }
}