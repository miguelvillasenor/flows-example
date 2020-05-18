package dev.mvillasenor.realtimestreams.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.mvillasenor.realtimestreams.data.TwitchRepositoryImpl
import dev.mvillasenor.realtimestreams.data.retrofit.TwitchApi
import javax.inject.Inject

class MainViewModel @Inject constructor(twitchApi: TwitchApi) : ViewModel() {

    private val twitchRepository = TwitchRepositoryImpl(twitchApi)

    val gamesLiveData = twitchRepository.observeTopGames()
        .asLiveData()

}
