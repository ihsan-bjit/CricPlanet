package com.ihsan.cricplanet.roomdb.db

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ihsan.cricplanet.roomdb.dao.CricDao

abstract class CricPlanetDatabase: RoomDatabase() {
    abstract fun newsDao(): CricDao
    companion object{
        @Volatile
        private var INSTANCE: CricPlanetDatabase?=null

        fun getDatabase(context: Context): CricPlanetDatabase {
            val tempInstance= INSTANCE

            if(tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    CricPlanetDatabase::class.java,
                    "cric_planet_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE =instance
                Log.d("TAG", "getDatabase Instance created: $INSTANCE")

                return instance
            }
        }
    }
}