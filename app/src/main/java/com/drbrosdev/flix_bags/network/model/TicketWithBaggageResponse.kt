package com.drbrosdev.flix_bags.network.model

import com.google.gson.annotations.SerializedName

data class TicketWithBaggageResponse(
    @SerializedName("message") val message: String,
    @SerializedName("counterTicket") val counterTicket: Int,
    @SerializedName("counterBaggage") val counterBaggage: Int
)
