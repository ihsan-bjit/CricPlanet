package com.ihsan.cricplanet.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.model.fixture.FixtureIncludeTeams
import com.ihsan.cricplanet.model.fixture.FixtureIncludeTeamsVenue
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

    init {
        //Getting dao instance
        CricDao = CricPlanetDatabase.getDatabase(application).CricDao()
        //Assigning dao object to repository instance
        repository = CricRepository(CricDao)
        getTeamsDB = repository.readTeams()
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
    fun getFixturesApi():MutableLiveData<List<FixtureIncludeTeamsVenue>> {
        val fixturesLiveData= MutableLiveData<List<FixtureIncludeTeamsVenue>>()
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    fixturesLiveData.value=repository.getFixturesApi()
                    Log.d("cricViewModel", "getFeatures: ${fixturesLiveData.value}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "getFeatures: $e")
                }
            }
        }
        return fixturesLiveData
    }

    fun getUpcomingFixturesApi():MutableLiveData<List<FixtureIncludeTeamsVenue>> {
        val upcomingFixturesLiveData = MutableLiveData<List<FixtureIncludeTeamsVenue>>()
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    upcomingFixturesLiveData.value=repository.getUpcomingFixturesApi()
                    Log.d("cricViewModel", "getFeatures: ${upcomingFixturesLiveData.value}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "getFeatures: $e")
                }
            }
        }
        return upcomingFixturesLiveData
    }
    fun getRecentFixturesApi():MutableLiveData<List<FixtureIncludeTeamsVenue>> {
        val recentFixturesLiveData= MutableLiveData<List<FixtureIncludeTeamsVenue>>()
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    recentFixturesLiveData.value=repository.getRecentFixturesApi()
                    Log.d("cricViewModel", "getFeatures: ${recentFixturesLiveData.value}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "getFeatures: $e")
                }
            }
        }
        return recentFixturesLiveData
    }
}