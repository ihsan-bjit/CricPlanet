package com.ihsan.cricplanet.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForCard
import com.ihsan.cricplanet.repository.CricRepository
import com.ihsan.cricplanet.roomdb.dao.CricDao
import com.ihsan.cricplanet.roomdb.db.CricPlanetDatabase
import kotlinx.coroutines.*
@OptIn(DelicateCoroutinesApi::class)
class CricViewModel(application: Application) : AndroidViewModel(application) {
    //Initialize repository object
    private val repository: CricRepository
    //Dao Initialize
    private var CricDao: CricDao

    val getTeamsDB: LiveData<List<Team>>

    private val _upcomingMatchFixture = MutableLiveData<List<FixtureIncludeForCard>>()
    val upcomingMatchFixture:LiveData<List<FixtureIncludeForCard>> =_upcomingMatchFixture
    private val _recentMatchFixture = MutableLiveData<List<FixtureIncludeForCard>>()
    val recentMatchFixture:LiveData<List<FixtureIncludeForCard>> =_recentMatchFixture
    private val _matchFixture = MutableLiveData<List<FixtureIncludeForCard>>()
    val matchFixture:LiveData<List<FixtureIncludeForCard>> =_matchFixture

    init {
        //Getting dao instance
        CricDao = CricPlanetDatabase.getDatabase(application).CricDao()
        //Assigning dao object to repository instance
        repository = CricRepository(CricDao)
        getTeamsDB = repository.readTeams()
        //getUpcomingFixturesApi()
        //getRecentFixturesApi()
        //getFixturesApi()
    }

    suspend fun storeLocal(apiTeamList: List<Team>?) {
        if (apiTeamList == null) {
            Log.d("teamApi", "TeamApi Size null return: null")
            return
        }
        Log.d("teamApi", "Team Size: ${apiTeamList.size}")
        repository.storeTeamsLocal(apiTeamList)
    }

    fun getTeams() {
        GlobalScope.launch {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    storeLocal(repository.getTeamsApi())
                } catch (e: java.lang.Exception) {
                    Log.d("teamCatch", "getTeam: $e")
                }
            }
        }
    }
    fun getFixturesApi() {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _matchFixture.value=repository.getFixturesApi()
                    Log.d("cricViewModel", "viewModel Api getFixture: ${matchFixture.value?.size}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "viewModel Api getFixture: $e")
                }
            }
        }
    }

    fun getUpcomingFixturesApi() {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _upcomingMatchFixture.value=repository.getUpcomingFixturesApi()
                    Log.d("cricViewModel", "viewModel Api getUpcomingFixture: ${upcomingMatchFixture.value?.size}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "getUpcomingFixture: $e")
                }
            }
        }
    }
    fun getRecentFixturesApi(){
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _recentMatchFixture.value=repository.getRecentFixturesApi()
                    Log.d("cricViewModel", "viewModel Api getRecentFixture: ${recentMatchFixture.value?.size}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "viewModel Api getRecentFixture: $e")
                }
            }
        }
    }
}