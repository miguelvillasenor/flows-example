package dev.mvillasenor.realtimestreams.data

import dev.mvillasenor.realtimestreams.data.retrofit.TwitchApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class TwitchRepositoryImpl(private val twitchApi: TwitchApi) : TwitchRepository {

    override fun observeTopGames(): Flow<List<Game>> =
        flow {
            while (true) {
                emit(Unit)
            }
        }
            .map { twitchApi.getTopGames().data }

    override fun observeStreamsFor(gameId: String): Flow<List<Stream>> =
        flow {
            while (true) {
                emit(Unit)
            }
        }
            .map { twitchApi.getStreams(gameId).data }
}
