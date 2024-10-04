package com.example.vknewsclientapp.di

import androidx.lifecycle.ViewModel
import com.example.vknewsclientapp.presentation.main.MainViewModel
import com.example.vknewsclientapp.presentation.news.NewsFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(NewsFeedViewModel::class)
    @Binds
    fun bindsNewsFeedViewModel(impl: NewsFeedViewModel): ViewModel

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindsMainViewModel(impl: MainViewModel): ViewModel

}