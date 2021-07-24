package com.example.weather.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommonViewModel : ViewModel() {
    protected val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error
}