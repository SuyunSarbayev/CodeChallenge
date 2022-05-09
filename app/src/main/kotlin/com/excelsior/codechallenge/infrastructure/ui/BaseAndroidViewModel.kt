package com.excelsior.codechallenge.infrastructure.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.excelsior.codechallenge.infrastructure.utils.SingleLiveEvent
import org.koin.core.component.KoinComponent

open class BaseAndroidViewModel : BaseViewModel, KoinComponent, ViewModel() {

    override val direction = SingleLiveEvent<NavDirections>()

    override val showError = MutableLiveData<String>()

    override val loader = SingleLiveEvent<Boolean>()
}