package com.drbrosdev.flix_bags.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow

class HomeViewModel : ViewModel() {

    private val _events = Channel<HomeEvents>()
    val events = _events.receiveAsFlow()

    private val customerBagCode = MutableStateFlow("")
    private val bagCode = MutableStateFlow("")

    val state = combine(
        customerBagCode,
        bagCode
    ) { getCustomerBagCode, getBagCode ->
        HomeUiState(
            customerBagCode = getCustomerBagCode,
            bagCode = getBagCode
        )
    }

    fun compare() {

    }
}