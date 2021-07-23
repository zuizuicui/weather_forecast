package com.example.weather.common.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class CommonFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attachComponent(savedInstanceState)
    }

    abstract fun attachComponent(savedInstanceState: Bundle?)
}