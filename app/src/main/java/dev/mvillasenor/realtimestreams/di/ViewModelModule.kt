package dev.mvillasenor.realtimestreams.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.mvillasenor.realtimestreams.ui.main.MainViewModel
import dev.mvillasenor.realtimestreams.ui.streams.StreamsViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StreamsViewModel::class)
    internal abstract fun streamsViewModel(viewModel: StreamsViewModel): ViewModel
}
