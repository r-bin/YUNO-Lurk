package org.yuno.apps.lurk.screens.main.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TwitchApi {
    @GET("/kraken/streams")
    fun getStreams(@Query("game") key: String): Call<Streams>
}

data class Streams(
    val streams: List<Stream>
)

data class Stream(
    val broadcast_platform: String,
    val viewers: Int,

    val channel: Channel,
    val preview: Preview
)

data class Channel(
    val followers: Int,
    val display_name: String,
    val broadcaster_language: String,
    val language: String,
    val logo: String,
    val url: String
)

data class Preview(
    val small: String,
    val medium: String,
    val large: String
)