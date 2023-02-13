package com.ihsan.cricplanet.repository

import androidx.lifecycle.LiveData
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.model.fixture.Fixture
import com.ihsan.cricplanet.model.fixture.FixtureIncludeTeams
import com.ihsan.cricplanet.network.CricApi
import com.ihsan.cricplanet.roomdb.dao.CricDao
import com.ihsan.cricplanet.utils.Constant

class CricRepository(private val cricDao: CricDao) {
    fun readTeams(): LiveData<List<Team>> {
        return cricDao.readTeams()
    }

    suspend fun storeTeamsLocal(team: List<Team>) {
        return cricDao.storeTeams(team)
    }

    suspend fun getTeamsApi():List<Team>{
        return CricApi.retrofitService.getTeamsResponse(Constant.API_KEY).data
    }

    suspend fun getFixturesApi():List<FixtureIncludeTeams>{
        return CricApi.retrofitService.getFixturesResponse("2023-01-15,2023-02-13","Finished","runs,localteam,visitorteam",Constant.API_KEY).data
    }
}