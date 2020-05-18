package dev.mvillasenor.realtimestreams.data

import dagger.Module
import dagger.Provides
import dev.mvillasenor.realtimestreams.data.retrofit.AuthorizationInterceptor
import dev.mvillasenor.realtimestreams.data.retrofit.TwitchApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesAuthenticationInterceptor() = AuthorizationInterceptor()

    @Provides
    @Singleton
    fun providesOkHttpClient(authorizationInterceptor: AuthorizationInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .build()


    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://api.twitch.tv/helix/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun providesTwitchApi(retrofit: Retrofit) = retrofit.create(TwitchApi::class.java)


    @Provides
    @Singleton
    fun providesTwitchRepository(twitchApi: TwitchApi): TwitchRepository =
        TwitchRepositoryImpl(twitchApi)
}
