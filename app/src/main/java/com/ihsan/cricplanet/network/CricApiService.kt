package com.ihsan.cricplanet.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL="https://newsapi.org/v2/"
private const val API_KEY="c66a23e611f54f2eaae2b485b7d2517b"
private const val PARAMETER="country=us&"
private val moshi= Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit=
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

class CricApiService {
    @GET("everything?apiKey=$API_KEY")
    suspend fun getNewsSearchApi(searchString:String):News
    @GET("top-headlines?${PARAMETER}apiKey=$API_KEY")
    suspend fun getNewsFromApi():News
}

object NewsApi{
    val retrofitService: CricApiService by lazy { retrofit.create(CricApiService::class.java) }
}