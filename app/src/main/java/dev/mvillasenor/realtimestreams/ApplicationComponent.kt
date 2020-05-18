package dev.mvillasenor.realtimestreams

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dev.mvillasenor.realtimestreams.data.DataModule
import dev.mvillasenor.realtimestreams.di.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, DataModule::class, ActivitiesModule::class, ViewModelModule::class])
interface ApplicationComponent : AndroidInjector<RealTimeStreamsApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
