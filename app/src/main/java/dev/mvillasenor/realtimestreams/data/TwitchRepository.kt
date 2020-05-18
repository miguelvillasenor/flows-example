package dev.mvillasenor.realtimestreams.data

import kotlinx.coroutines.flow.Flow

interface TwitchRepository {
    fun observeTopGames(): Flow<List<Game>>
    fun observeStreamsFor(gameId: String): Flow<List<Stream>>
}
