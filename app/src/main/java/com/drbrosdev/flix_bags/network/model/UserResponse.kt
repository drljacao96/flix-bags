package com.drbrosdev.flix_bags.network.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("email_verified_at") val emailVerifiedAt: String?,
    @SerializedName("phone_number") val phoneNumber: String?,
    @SerializedName("user_type") val userType: String?,
    @SerializedName("company_id") val companyId: Int?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
