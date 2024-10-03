package com.example.vknewsclientapp.domain.entity

sealed class AuthState {

    object Initial: AuthState()

    object Authorized: AuthState()

    object NotAuthorized: AuthState()

}