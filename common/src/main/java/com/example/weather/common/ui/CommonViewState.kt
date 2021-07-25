package com.example.weather.common.ui

import androidx.annotation.StringRes

interface ViewState

enum class CommonViewState : ViewState {
    LOADING,
    EMPTY,
    HAS_RESULT,
    NO_RESULT,
    ERROR
}

interface ErrorEvent {
    @StringRes
    fun getErrorResource(): Int
}

