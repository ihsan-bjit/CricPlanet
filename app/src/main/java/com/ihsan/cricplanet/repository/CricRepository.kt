package com.ihsan.cricplanet.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.model.fixture.Fixture
import com.ihsan.cricplanet.model.fixture.FixtureIncludeTeams
import com.ihsan.cricplanet.model.fixture.FixtureIncludeTeamsVenue
import com.ihsan.cricplanet.network.CricApi
import com.ihsan.cricplanet.roomdb.dao.CricDao
import com.ihsan.cricplanet.utils.Constant
import com.ihsan.cricplanet.utils.Utils

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

    suspend fun getFixturesApi():List<FixtureIncludeTeamsVenue>{
        return CricApi.retrofitService.getFixturesResponse("2022-01-15,2024-02-13","","localteam,visitorteam,venue",Constant.API_KEY).data
    }

    suspend fun getUpcomingFixturesApi():List<FixtureIncludeTeamsVenue>{
        return CricApi.retrofitService.getFixturesResponse(Utils().upcomingYearDuration(),"NS","localteam,visitorteam,venue",Constant.API_KEY).data
    }

    suspend fun getRecentFixturesApi():List<FixtureIncludeTeamsVenue>{
        return CricApi.retrofitService.getFixturesResponse(Utils().recentMonthDuration(),"Finished","localteam,visitorteam,venue",Constant.API_KEY).data
    }
}