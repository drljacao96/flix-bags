package com.drbrosdev.flix_bags.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drbrosdev.flix_bags.data.TokenDataSource
import com.drbrosdev.flix_bags.network.TicketApi
import com.drbrosdev.flix_bags.network.model.LoginRequest
import com.drbrosdev.flix_bags.network.model.ResultWrapper
import com.drbrosdev.flix_bags.network.model.executeWithResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import logcat.logcat

class LoginViewModel(
    private val tokenDataSource: TokenDataSource
) : ViewModel() {

    private val _events = Channel<LoginEvents>()
    val events = _events.receiveAsFlow()

    private val loading = MutableStateFlow(false)

    val state = loading

    init {
        /*
        todo: Check token validity, if valid proceed to home screen with event, if not need login again
         */
    }

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            loading.update {
                true
            }
            if (userName.isBlank() or password.isBlank()) {
                _events.send(LoginEvents.InputFieldsEmpty)
                loading.update {
                    false
                }
                return@launch
            }
            val response = executeWithResult {
                TicketApi.ticketApiService.login(
                    loginRequest = LoginRequest(userName, password)
                )
            }

            when (response) {
                is ResultWrapper.Success -> {
                    if(response.value.response == "error") {
                        _events.send(LoginEvents.LoginRequestError)
                    } else {
                        val token = response.value.result.token
                        logcat { "Token response in success case: --> $token" }
                        token?.let {
                            tokenDataSource.updateToken(it)
                            logcat { "token saved to datastore" }
                        }
                        _events.send(LoginEvents.NavigateToHome)
                    }
                }
                is ResultWrapper.GenericError -> {
                    //400-599 error from the request itself
                    _events.send(
                        LoginEvents.ShowNetworkCallError(
                            code = response.code ?: 0,
                            message = response.error ?: ""
                        )
                    )
                }
                is ResultWrapper.NetworkError -> {
                    //no internet connection error
                    _events.send(LoginEvents.ShowInternetConnectionError)
                }
            }
            loading.update {
                false
            }
        }
    }

}