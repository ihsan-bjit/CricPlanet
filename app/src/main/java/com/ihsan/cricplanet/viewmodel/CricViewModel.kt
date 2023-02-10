package com.ihsan.cricplanet.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.network.CricApi
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

    val getTeams: LiveData<List<Team>>

    init {
        //Getting dao instance
        CricDao = CricPlanetDatabase.getDatabase(application).CricDao()
        //Assigning dao object to repository instance
        repository = CricRepository(CricDao)
        getTeams = repository.readTeams()
        getFeatures()
    }

//    fun getBookmarks(): LiveData<List<NewsTable>> {
//        return repository.readBookmarksNews()
//    }


    suspend fun storeLocal(apiTeamList: List<Team>?) {
        if (apiTeamList == null) {
            Log.d("teamApi", "TeamApi Size null return: null")
            return
        }
        Log.d("teamApi", "Team Size: ${apiTeamList.size}")
        repository.storeTeamsLocal(apiTeamList)
    }

    fun getTeams() {
        viewModelScope.launch {
            try {
                storeLocal(repository.getTeamsApi())
            } catch (e: java.lang.Exception) {
                Log.d("teamCatch", "getTeam: $e")
            }
        }
    }
    fun getFeatures() {
        viewModelScope.launch {
            try {
                Log.d("cricViewModel", "getFeatures: ${repository.getTeamsApi()}")
            } catch (e: java.lang.Exception) {
                Log.d("cricViewModelCatch", "getTeam: $e")
            }
        }
    }
}