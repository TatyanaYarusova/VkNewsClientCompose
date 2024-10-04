package com.example.vknewsclientapp.di

import android.content.Context
import com.example.vknewsclientapp.data.network.ApiFactory
import com.example.vknewsclientapp.data.network.ApiService
import com.example.vknewsclientapp.data.repository.NewsFeedRepositoryImpl
import com.example.vknewsclientapp.domain.repository.NewsFeedRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService

        @ApplicationScope
        @Provides
        fun provideVKStorage(context: Context): VKPreferencesKeyValueStorage =
            VKPreferencesKeyValueStorage(context)

    }
}