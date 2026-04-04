package com.example.recyclerviewex.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BaseCreateFactoryViewModel<T : ViewModel>(
    private val creator: () -> T
) : ViewModelProvider.Factory {

    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
        return creator() as VM
    }
}