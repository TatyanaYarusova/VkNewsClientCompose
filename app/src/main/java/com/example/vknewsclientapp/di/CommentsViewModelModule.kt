package com.example.vknewsclientapp.di

import androidx.lifecycle.ViewModel
import com.example.vknewsclientapp.presentation.comments.CommentsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CommentsViewModelModule {
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    @Binds
    fun bindsCommentsViewModel(impl: CommentsViewModel): ViewModel
}