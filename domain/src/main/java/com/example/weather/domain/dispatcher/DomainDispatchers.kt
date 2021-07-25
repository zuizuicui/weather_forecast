package com.example.weather.domain.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class DomainDispatchers @Inject constructor (val default : CoroutineDispatcher)