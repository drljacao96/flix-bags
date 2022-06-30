package com.drbrosdev.flix_bags.presentation.home

sealed class HomeEvents {
    object CustomerBagCodeScanned : HomeEvents()
    object CodeMatch : HomeEvents()
    object CodeNotMatch : HomeEvents()
    object CodesScanned : HomeEvents()
}