package com.me.instafeed.services

class GiphyRepository {
    fun getRandom(key: String) = RetrofitBuilder.apiService.getRandom(key)
    fun getTrending(key: String) = RetrofitBuilder.apiService.getTrending(key)
}