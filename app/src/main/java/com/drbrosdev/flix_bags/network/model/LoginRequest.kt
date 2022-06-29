package com.drbrosdev.flix_bags.network.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") val userName: String,
    @SerializedName("password") val password: String
)
