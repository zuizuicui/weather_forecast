package com.example.weather.domain.interaction

import kotlinx.coroutines.flow.Flow

interface FlowUseCase <I, O> {
    operator fun invoke(input: I) : Flow<Result<O>>
}