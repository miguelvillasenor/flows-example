package dev.mvillasenor.realtimestreams.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.mvillasenor.realtimestreams.data.TwitchRepositoryImpl
import dev.mvillasenor.realtimestreams.data.retrofit.TwitchApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(twitchApi: TwitchApi) : ViewModel() {

    private val filterChannel = Channel<String>()
    private val twitchRepository = TwitchRepositoryImpl(twitchApi)

    val gamesLiveData = twitchRepository.observeTopGames()
        .combine(filterChannel.receiveAsFlow()) { games, filter ->
            if (filter.isEmpty()) {
                games
            } else {
                games
                    .filter { it.name.contains(filter, true) }
            }
        }
        .asLiveData()

    fun applyTextFilter(filter: String) {
        filterChannel.offer(filter)
    }

}
