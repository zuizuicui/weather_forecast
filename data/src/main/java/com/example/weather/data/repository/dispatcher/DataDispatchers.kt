package com.example.weather.data.repository.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class DataDispatchers @Inject constructor (val io: CoroutineDispatcher, val default: CoroutineDispatcher)