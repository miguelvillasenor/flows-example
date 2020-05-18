package dev.mvillasenor.realtimestreams

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class RealTimeStreamsApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
