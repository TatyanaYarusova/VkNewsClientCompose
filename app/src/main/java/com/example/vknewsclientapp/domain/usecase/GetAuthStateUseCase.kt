package com.example.vknewsclientapp.domain.usecase

import com.example.vknewsclientapp.domain.entity.AuthState
import com.example.vknewsclientapp.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}