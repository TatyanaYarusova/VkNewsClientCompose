package com.example.vknewsclientapp.domain.usecase

import com.example.vknewsclientapp.domain.repository.NewsFeedRepository
import javax.inject.Inject

class LoadNestDataUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        return repository.loadNextData()
    }
}