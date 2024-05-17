package com.jhostinluna.heroes.core.di

import com.jhostinluna.heroes.BuildConfig
import com.jhostinluna.heroes.features.data.CharacterApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {
    @Provides
    fun getApiRetrofitService(): CharacterApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterApiService::class.java)


}