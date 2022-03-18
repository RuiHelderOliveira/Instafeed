package com.me.instafeed

data class DataResponse(
    val type: String,
    val id: String,
    val url: String,
    val username: String,
    val title: String,
    val rating: String,
    val images: ImageResponse
)

data class ImageResponse(val original: Original)

data class Original(
    val frames: String,
    val hash: String,
    val height: String,
    val mp4: String,
    val mp4_size: String,
    val size: String,
    val url: String,
    val webp: String,
    val webp_size: String,
    val width: String
)
