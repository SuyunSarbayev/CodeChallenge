package com.excelsior.codechallenge.infrastructure.ui

import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.excelsior.codechallenge.infrastructure.utils.SingleLiveEvent

interface BaseViewModel {

    val direction: SingleLiveEvent<NavDirections>

    val showError: LiveData<String>

    val loader: SingleLiveEvent<Boolean>
}