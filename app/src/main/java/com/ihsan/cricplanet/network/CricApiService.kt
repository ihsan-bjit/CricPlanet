package com.ihsan.cricplanet.network

import com.ihsan.cricplanet.model.responseapi.ResponseTeam
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL="https://cricket.sportmonks.com/api/v2.0/"
private const val API_KEY="81gSXinE4LtZQ7LiZhwEjYbIvpDElRRlUw4dUEEgxlRgHLVMrIB963vfbYms"
private const val PARAMETER="include=us&"
private val moshi= Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit=
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface CricApiService {
    @GET("teams?api_token=$API_KEY")
    suspend fun getTeamsApi():ResponseTeam
}

object CricApi{
    val retrofitService: CricApiService by lazy { retrofit.create(CricApiService::class.java) }
}