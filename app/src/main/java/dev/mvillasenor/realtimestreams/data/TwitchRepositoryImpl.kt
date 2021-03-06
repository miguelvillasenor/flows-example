package dev.mvillasenor.realtimestreams.data

import dev.mvillasenor.realtimestreams.data.retrofit.TwitchApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import timber.log.Timber

class TwitchRepositoryImpl(private val twitchApi: TwitchApi) : TwitchRepository {

    override fun observeTopGames(): Flow<List<Game>> = twentySecondsFlow()
        .transform {
            try {
                Timber.d("Fetching games ...")
                emit(twitchApi.getTopGames().data)
            } catch (exception: Exception) {
                Timber.e(exception, "Error fetching Games")
            }
        }


    override fun observeStreamsFor(gameId: String): Flow<List<Stream>> = twentySecondsFlow()
        .transform {
            try {
                Timber.d("Fetching streams ...")
                emit(twitchApi.getStreams(gameId).data)
            } catch (exception: Exception) {
                Timber.e(exception, "Error fetching Streams")
            }
        }

    private fun twentySecondsFlow() = flow {
        while (true) {
            emit(Unit)
            delay(20_000)
        }
    }

}
