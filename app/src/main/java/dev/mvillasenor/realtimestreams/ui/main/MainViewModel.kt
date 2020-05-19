package dev.mvillasenor.realtimestreams.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dev.mvillasenor.realtimestreams.data.TwitchRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    twitchRepository: TwitchRepository
) : ViewModel() {

    private val filterChannel = Channel<String>()

    val gamesLiveData = twitchRepository.observeTopGames()
        .combine(filterChannel.receiveAsFlow()) { games, filter ->
            if (filter.isEmpty()) {
                games
            } else {
                games
                    .filter { it.name.contains(filter, true) }
            }
        }
        .asLiveData(viewModelScope.coroutineContext)

    fun applyTextFilter(filter: String) {
        filterChannel.offer(filter)
    }

}
