package com.drbrosdev.flix_bags.presentation.home

sealed class HomeEvents {
    object CustomerBagCodeScanned 
    object CodeMatch : HomeEvents()
    object CodeNotMatch : HomeEvents()
}