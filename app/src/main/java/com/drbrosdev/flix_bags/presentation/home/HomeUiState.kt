package com.drbrosdev.flix_bags.presentation.home

data class HomeUiState(
    val customerBagCode: String = "",
    val bagCode: String = "",
    val isCustomerBagCodeScanned: Boolean = false,
    val codesMatch: Boolean = false
) {
}