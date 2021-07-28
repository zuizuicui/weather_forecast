package com.example.weather.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class CommonViewModel : ViewModel() {
    protected val _viewState = MutableLiveData<ViewState>(CommonViewState.EMPTY)
    val viewState : LiveData<ViewState> = _viewState
}