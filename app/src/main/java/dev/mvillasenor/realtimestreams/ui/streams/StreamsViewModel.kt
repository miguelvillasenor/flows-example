package dev.mvillasenor.realtimestreams.ui.streams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dev.mvillasenor.realtimestreams.data.TwitchRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class StreamsViewModel @Inject constructor(
    private val twitchRepository: TwitchRepository
) : ViewModel() {

    private val filterChannel = Channel<String>()

    fun getStreamsLiveData(gameId: String) = twitchRepository
        .observeStreamsFor(gameId)
        .combine(filterChannel.receiveAsFlow()) { games, filter ->
            if (filter.isEmpty()) {
                games
            } else {
                games.filter { it.title.contains(filter, true) }
            }
        }
        .asLiveData(viewModelScope.coroutineContext)

    fun applyTextFilter(filter: String) {
        filterChannel.offer(filter)
    }
}
