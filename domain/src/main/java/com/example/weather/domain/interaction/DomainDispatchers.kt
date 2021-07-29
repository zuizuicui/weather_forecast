package com.example.weather.domain.interaction

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class DomainDispatchers @Inject constructor (val default : CoroutineDispatcher)