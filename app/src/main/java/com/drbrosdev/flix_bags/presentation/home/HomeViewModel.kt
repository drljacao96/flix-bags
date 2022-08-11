package com.drbrosdev.flix_bags.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _events = Channel<HomeEvents>()
    val events = _events.receiveAsFlow()

    private val customerBagCode = stateHandle.getStateFlow(CUSTOMER_CODE, "")
    private val bagCode = stateHandle.getStateFlow(BAG_CODE, "")
    private val comparisonState = MutableStateFlow(CodeComparisonState.IDLE)

    val state = combine(
        customerBagCode,
        bagCode,
        comparisonState
    ) { getCustomerBagCode, getBagCode, getComparisonState ->
        HomeUiState(
            customerBagCode = getCustomerBagCode,
            bagCode = getBagCode,
            comparisonState = getComparisonState
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeUiState())

    fun submitCustomerBagCode(bagRawValue: String) {
        if (customerBagCode.value.isNotBlank() and bagCode.value.isNotBlank()) {
            comparisonState.update { CodeComparisonState.IDLE }
            stateHandle[CUSTOMER_CODE] = ""
            stateHandle[BAG_CODE] = ""
        }

        if (customerBagCode.value.isBlank()) {
            stateHandle[CUSTOMER_CODE] = bagRawValue
            compareBags(customerBagCode.value, bagCode.value)
            return
        }

        if (bagCode.value.isBlank()) {
            stateHandle[BAG_CODE] = bagRawValue
            compareBags(customerBagCode.value, bagCode.value)
            return
        }
    }

    fun updateComparisonState(codeComparison: CodeComparisonState) {
        comparisonState.update {
            codeComparison
        }
        stateHandle[BAG_CODE] = ""
        stateHandle[CUSTOMER_CODE] = ""
    }

    private fun compareBags(firstCode: String, secondCode: String) {
        if (firstCode.isBlank() or secondCode.isBlank())
            return

        viewModelScope.launch {
            if (firstCode == secondCode) {
                _events.send(HomeEvents.CodeMatch)
                comparisonState.update { CodeComparisonState.TRUE }
            } else {
                _events.send(HomeEvents.CodeNotMatch)
                comparisonState.update { CodeComparisonState.FALSE }
            }
        }
    }

    fun setHeaderText(comparisonState: CodeComparisonState): String {
        return when (comparisonState) {
            CodeComparisonState.FALSE -> {
                "QR KOD JE NEISPRAVAN"
            }
            CodeComparisonState.TRUE -> {
                "QR KOD JE ISPRAVAN"
            }
            else -> computeScanInstructionText()
        }
    }

    private fun computeScanInstructionText(): String {
        return if (customerBagCode.value.isBlank() && bagCode.value.isBlank()) "Skeniraj KOD od PUTNIKA"
        else if (customerBagCode.value.isNotBlank() && bagCode.value.isBlank()) "Skeniraj KOD sa TORBE"
        else ""
    }

    fun setText(comparisonState: CodeComparisonState): String {
        return if (comparisonState == CodeComparisonState.FALSE ||
            comparisonState == CodeComparisonState.TRUE
        )
            "IMA LI JOS PRTLJAGA U BOKSU?"
        else ""
    }

    companion object {
        private const val CUSTOMER_CODE = "customer_code"
        private const val BAG_CODE = "bag_code"
    }
}