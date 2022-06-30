package com.drbrosdev.flix_bags.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _events = Channel<HomeEvents>()
    val events = _events.receiveAsFlow()

    private val customerBagCode = MutableStateFlow("")
    private val bagCode = MutableStateFlow("")
    private val isCustomerBagCodeScanned = MutableStateFlow(false)
    private val codesMatch = MutableStateFlow(false)

    val state = combine(
        customerBagCode,
        bagCode,
        isCustomerBagCodeScanned,
        codesMatch
    ) { getCustomerBagCode, getBagCode, isCustomerBagCodeScanned, doCodesMatch ->
        HomeUiState(
            customerBagCode = getCustomerBagCode,
            bagCode = getBagCode,
            isCustomerBagCodeScanned = isCustomerBagCodeScanned,
            codesMatch = doCodesMatch
        )
    }

    fun submitCustomerBagCode(customerBagRawValue: String) {
        isCustomerBagCodeScanned.update {
            true
        }
        customerBagCode.update {
            customerBagRawValue
        }
    }

    fun compareBags(bagRawValue: String) {
        viewModelScope.launch {

            if(bagRawValue == customerBagCode.value) {
                _events.send(HomeEvents.CodeMatch)
            } else {
                _events.send(HomeEvents.CodeNotMatch)
            }

            isCustomerBagCodeScanned.update {
                false
            }

            customerBagCode.update {
                ""
            }
        }
    }
}