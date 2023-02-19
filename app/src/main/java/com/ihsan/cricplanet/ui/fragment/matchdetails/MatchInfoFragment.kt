package com.ihsan.cricplanet.ui.fragment.matchdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.viewmodel.CricViewModel

class MatchInfoFragment() : Fragment() {
    private val viewmodel: CricViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var matchId: Int? = 0
        arguments?.let {
            matchId = it.getString("matchId")?.toInt()
            Toast.makeText(requireContext(), it.getString("matchId"), Toast.LENGTH_SHORT).show()
        }

        viewmodel.fixtureByIdWithDetails.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}