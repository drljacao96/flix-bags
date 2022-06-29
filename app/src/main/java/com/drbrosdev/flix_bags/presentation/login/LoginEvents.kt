package com.drbrosdev.flix_bags.presentation.login

sealed class LoginEvents {
    object NavigateToHome: LoginEvents()
    object ShowInternetConnectionError: LoginEvents()
    data class ShowNetworkCallError(val code: Int, val message: String): LoginEvents()
    object InputFieldsEmpty: LoginEvents()
    object LoginRequestError: LoginEvents()
}
