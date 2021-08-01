package com.example.weather.domain.interaction.searchweather

import com.example.weather.domain.entity.LengthSearchKeyRule
import com.example.weather.domain.interaction.SuspendUseCase
import javax.inject.Inject

class GetSearchKeyLengthUseCase @Inject constructor () : SuspendUseCase<Unit?, Int> {
    suspend operator fun invoke() = invoke(null)
    override suspend fun invoke(input: Unit?) = LengthSearchKeyRule.searchWeatherMinLength
}