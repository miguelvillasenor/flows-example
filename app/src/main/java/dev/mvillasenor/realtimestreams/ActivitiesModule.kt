package dev.mvillasenor.realtimestreams

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.mvillasenor.realtimestreams.ui.main.MainActivity

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}
