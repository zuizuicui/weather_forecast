package com.example.weather.common.ui

import androidx.annotation.StringRes

sealed interface ViewState

enum class CommonViewState : ViewState {
    LOADING,
    EMPTY,
    HAS_RESULT,
    NO_RESULT_RESPONSE,
    NETWORK_ERROR,
    UN_KNOW_ERROR
}

interface ErrorEvent : ViewState {
    @StringRes
    fun getErrorResource(): Int
}

