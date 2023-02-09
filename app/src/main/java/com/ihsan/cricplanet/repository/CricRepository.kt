package com.ihsan.cricplanet.repository

import androidx.lifecycle.LiveData
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.roomdb.dao.CricDao

class CricRepository(private val cricDao: CricDao) {
    fun readTeams(): LiveData<List<Team>> {
        return cricDao.readTeams()
    }

    suspend fun storeTeamsLocal(team: List<Team>) {
        return cricDao.storeTeams(team)
    }
}