package dev.mvillasenor.realtimestreams.data.retrofit

import dev.mvillasenor.realtimestreams.data.Game
import dev.mvillasenor.realtimestreams.data.Response
import dev.mvillasenor.realtimestreams.data.Stream
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitchApi {

    @GET("games/top")
    suspend fun getTopGames(): Response<Game>

    @GET("streams")
    suspend fun getStreams(@Query("game_id") gameId: String): Response<Stream>

}
