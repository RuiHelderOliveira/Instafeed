package com.me.instafeed

data class TrendingResponse(
    val data: ArrayList<DataResponse>,
    val pagination: Pagination,
    val meta: Meta
)

data class Pagination(
    val total_count: Int,
    val count: Int,
    val offset: Int
)