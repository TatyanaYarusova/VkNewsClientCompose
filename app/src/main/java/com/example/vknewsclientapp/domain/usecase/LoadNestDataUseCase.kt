package com.example.vknewsclientapp.domain.usecase

import com.example.vknewsclientapp.domain.repository.NewsFeedRepository

class LoadNestDataUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        return repository.loadNextData()
    }
}