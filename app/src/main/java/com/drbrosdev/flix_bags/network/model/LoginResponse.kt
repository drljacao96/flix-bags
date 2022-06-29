package com.drbrosdev.flix_bags.network.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("response") val response: String,
    @SerializedName("result") val result: LoginResult
)