package com.example.weather.domain.interaction

interface SuspendUseCase <I, O> {
    suspend operator fun invoke(input: I) : O
}