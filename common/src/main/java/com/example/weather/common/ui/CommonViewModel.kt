package com.example.weather.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.domain.entity.exception.NetworkErrorException
import java.lang.Exception

abstract class CommonViewModel : ViewModel() {
    protected val _viewState = MutableLiveData<ViewState>(CommonViewState.EMPTY)
    val viewState : LiveData<ViewState> = _viewState

    fun handleCommonError(e: Exception): ViewState {
        return when(e) {
            is NetworkErrorException -> CommonViewState.NETWORK_ERROR
            else -> CommonViewState.UN_KNOW_ERROR
        }
    }
}