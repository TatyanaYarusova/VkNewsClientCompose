package com.example.vknewsclientapp.di

import android.content.Context
import com.example.vknewsclientapp.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }

    fun getCommentsScreenComponentFactory(): CommentsScreenComponent.Factory

    fun getViewModelFactory(): ViewModelFactory
}