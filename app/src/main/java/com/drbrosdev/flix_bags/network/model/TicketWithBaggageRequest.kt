package com.drbrosdev.flix_bags.network.model

import com.google.gson.annotations.SerializedName

data class TicketWithBaggageRequest(
    @SerializedName("busLineId") val busLineId: Int = 1,
    @SerializedName("ticketRawValue") val ticketRawValue: String = "",
    @SerializedName("baggageRawValue") val baggageRawValues: List<String> = emptyList()
)
