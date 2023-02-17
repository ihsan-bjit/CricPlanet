package com.ihsan.cricplanet.adapter

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihsan.cricplanet.model.Tab
import com.ihsan.cricplanet.ui.fragment.matchdetails.MatchInfoFragment
import com.ihsan.cricplanet.ui.fragment.matchdetails.MatchScorecardFragment
import com.ihsan.cricplanet.ui.fragment.matchdetails.MatchSquadsFragment
import com.ihsan.cricplanet.utils.MyApplication

class TabMatchDetailAdapter (manager: FragmentManager, lifecycle: Lifecycle, private val matchId:Int): FragmentStateAdapter(manager,lifecycle) {
    companion object{
        private var matchIdObj=MutableLiveData<Int>()
        val listMatchDetailTab = listOf(
            Tab(MatchInfoFragment(matchIdObj.value ?:0), "INFO"),
            Tab(MatchSquadsFragment(), "SQUADS"),
            Tab(MatchScorecardFragment(), "SCORECARD")
        )
    }
    override fun getItemCount(): Int {
        return  listMatchDetailTab.size
    }
    fun setMatchId(match:Int){
        matchIdObj.value=match
    }

    override fun createFragment(position: Int): Fragment {
        Log.d("cricTab", "Match Details createFragment: $matchId")
        return listMatchDetailTab[position].fragment
    }
}