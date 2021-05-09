package org.yuno.apps.lurk.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.yuno.apps.lurk.screens.main.model.Stream
import org.yuno.apps.lurk.screens.main.model.Streams
import org.yuno.apps.lurk.screens.main.model.TwitchApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val database: Database
) : ViewModel() {

    var client = OkHttpClient.Builder().addInterceptor(Interceptor {
        Log.i("test", "intercepted")

        val newRequest = it.request().newBuilder()
                .addHeader("Accept", "application/vnd.twitchtv.v5+json")
                .addHeader("Client-ID", "cagi684bmwa343s3un4tbfikqdog9r")
                .build()

        it.proceed(newRequest)
    }).build()

    var retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    public fun getStreamList(game: String, callback: (List<Stream>)->Unit) {
        val request = buildService(TwitchApi::class.java)
        val call = request.getStreams(game)

        call.enqueue(object: Callback<Streams> {
            override fun onResponse(call: Call<Streams>, response: Response<Streams>) {
                val streams = response.body()?.streams?.sortedByDescending { it.viewers }
                val streams_en = streams?.filter { it.channel.broadcaster_language == "en" }

                Log.i("test", "${streams?.count()} streams found: (${streams_en?.count()} english)")

                streams_en?.forEach {
                    Log.i("test", "${it.channel.display_name} (${it.viewers})")
                }

                callback(streams_en!!)
            }

            override fun onFailure(call: Call<Streams>, t: Throwable) {
                Log.i("test", "failure")
            }
        })
    }

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}