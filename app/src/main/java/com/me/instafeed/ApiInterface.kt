package com.me.instafeed

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v1/gifs/random")
    fun getRandom(@Query("api_key") api_key: String?): Call<RandomResponse>

    @GET("/v1/gifs/trending")
    fun getTrending(@Query("api_key") api_key: String?): Call<TrendingResponse>
}