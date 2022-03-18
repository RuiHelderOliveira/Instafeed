package com.me.instafeed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(val repo: GiphyRepository) : ViewModel() {

    private val _dataFailureResult = MutableLiveData<String>()
    val resultFailureLiveData = _dataFailureResult
    private val _dataTrendResult = MutableLiveData<Response<TrendingResponse>>()
    val resultTrendLiveData = _dataTrendResult
    private val _dataRandResult = MutableLiveData<Response<RandomResponse>>()
    val resultRandLiveData = _dataRandResult

    fun getRandom() {
        repo.getRandom("dUVV2nQ9Yx4MEyY7N6z5782sY1u5GAj2")
            .enqueue(object : Callback<RandomResponse> {
                override fun onFailure(call: Call<RandomResponse>, t: Throwable) {
                    resultFailureLiveData.postValue(t.message)
                }

                override fun onResponse(call: Call<RandomResponse>, response: Response<RandomResponse>) {
                    _dataRandResult.postValue(response)
                }
            })
    }

    fun getTrending() {
        repo.getTrending("dUVV2nQ9Yx4MEyY7N6z5782sY1u5GAj2")
            .enqueue(object : Callback<TrendingResponse> {
                override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                    resultFailureLiveData.postValue(t.message)
                }

                override fun onResponse(call: Call<TrendingResponse>, response: Response<TrendingResponse>) {
                    _dataTrendResult.postValue(response)
                }
            })
    }
}