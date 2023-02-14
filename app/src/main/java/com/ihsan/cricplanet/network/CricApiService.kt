package com.ihsan.cricplanet.network

import com.ihsan.cricplanet.model.responseapi.ResponseFixtureIncludeTeamsVenue
import com.ihsan.cricplanet.model.responseapi.ResponseTeam
import com.ihsan.cricplanet.utils.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://cricket.sportmonks.com/api/v2.0/"
private const val PARAMETER = "include=us&"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
        .build()

interface CricApiService {
    @GET(Constant.teams)
    suspend fun getTeamsResponse(@Query("${Constant.api_token}") api_token: String): ResponseTeam

    @GET(Constant.fixtures)
    suspend fun getFixturesResponse(
        @Query(Constant.filterDate) startsBetween: String,
        @Query(Constant.filterByStatus) filterByStatus: String,
        @Query(Constant.include) include: String,
        @Query(Constant.api_token) api_token: String
    ): ResponseFixtureIncludeTeamsVenue
}

object CricApi {
    val retrofitService: CricApiService by lazy { retrofit.create(CricApiService::class.java) }
}