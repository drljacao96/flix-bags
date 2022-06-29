package com.drbrosdev.flix_bags.network.model

import com.google.gson.annotations.SerializedName

data class LoginResult(
    @SerializedName("token") val token: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("user") val user: UserResponse
)
