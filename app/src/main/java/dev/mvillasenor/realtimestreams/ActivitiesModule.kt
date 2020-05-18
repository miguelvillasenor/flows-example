package dev.mvillasenor.realtimestreams

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.mvillasenor.realtimestreams.ui.main.MainActivity
import dev.mvillasenor.realtimestreams.ui.streams.StreamsActivity

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun streamsActivity(): StreamsActivity
}
