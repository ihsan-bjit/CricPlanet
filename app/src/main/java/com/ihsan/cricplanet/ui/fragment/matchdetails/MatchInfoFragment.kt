package com.ihsan.cricplanet.ui.fragment.matchdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.adapter.viewpager.TabMatchDetailAdapter

class MatchInfoFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { Toast.makeText(requireContext(), it.getString("matchId"), Toast.LENGTH_SHORT).show() }
    }
}